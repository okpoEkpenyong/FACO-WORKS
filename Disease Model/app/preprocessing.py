import librosa as lb
import sys
# So we can import from cough_inference
sys.path.append('../..')
from cough_inference import arr2image

def convert_to_spec_image(filename):
    """ Takes wav file and converts it to fastai image object """
    y, sr = lb.load(filename)
    src_ft = lb.stft(y)
    src_db = lb.amplitude_to_db(abs(src_ft))
    img = arr2image(src_db, do_normalize=True)
    return img



