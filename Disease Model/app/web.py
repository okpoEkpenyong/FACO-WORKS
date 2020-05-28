import os
from flask import Flask, flash, render_template, request, redirect, url_for, send_from_directory
from werkzeug.utils import secure_filename
from werkzeug.middleware.proxy_fix import ProxyFix
from flask_ngrok import run_with_ngrok
from utils import get_heartrate, convert_vid

ALLOWED_EXTENSIONS = set(['.wav, .aif, .aiff, mp3, mpeg, mov, mid, rm, ra, wma, asf'])   #, 'mov', 'avi'])

app = Flask(__name__)
run_with_ngrok(app)   
app.config['UPLOAD_FOLDER'] = 'upload/'
app.secret_key = os.environ.get('SECRET_KEY', 'dev')

def allowed_file(filename):
    return '.' in filename and \
           filename.rsplit('.', 1)[1].lower() in ALLOWED_EXTENSIONS

@app.route('/', methods=['GET', 'POST'])
def upload_file():
    if request.method == 'POST':
        # check if the post request has the file part
        if 'file' not in request.files:
            flash('No file part')
            return redirect(request.url)
        file = request.files['file']
        # if user does not select file, browser also
        # submit an empty part without filename
        if file.filename == '':
            flash('No selected file')
            return redirect(request.url)
        if file and allowed_file(file.filename):
            filename = secure_filename(file.filename)
            file.save(os.path.join(app.config['UPLOAD_FOLDER'], filename))
            filename = convert_audio(filename, app.config['UPLOAD_FOLDER'])
            if filename:
                return redirect(url_for('show_heartrate',
                                        filename=filename))
            else:
                flash('Could not read the file')
                return redirect(request.url)
        else:
            flash('File not allowed')
            return redirect(request.url)
    else:
            return render_template('upload.html')

@app.route('/heartrate/<filename>')
def show_heartrate(filename):
    hr, video = get_heartrate(filename, app.config['UPLOAD_FOLDER'])
    return render_template('results.html', heartrate=str(hr), video=video)

if __name__ == "__main__":
    app.run()
