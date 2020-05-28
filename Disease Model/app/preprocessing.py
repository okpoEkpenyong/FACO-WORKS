import librosa as lb
from librosa.display import specshow
import matplotlib.pyplot as plt



def convert_to_spec_image(filename):

    y, sr = lb.load(filename)
    # Plot signal in
    plt.figure(figsize=(10, 3))
    src_ft = lb.stft(y)
    src_db = lb.amplitude_to_db(abs(src_ft))
    specshow(src_db, sr=sr, x_axis='time', y_axis='hz')
    plt.ylim(0, 5000)
    filename = plt.savefig(filename+ '.png')
    return filename



