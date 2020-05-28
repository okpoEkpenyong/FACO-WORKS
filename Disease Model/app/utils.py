from fastai.basic_train import load_learner
import preprocessing
import librosa as lb
import os, glob
import numpy as np
import subprocess

def get_model(model, dir='.'):
    """ exported model file and dir """
    return load_learner(dir, model)

def get_diagnosis(filename, dir):
    """ Read video file and estimate heart rate """
    # Preprocessing phase
    c = -1 # Valor por defecto

    fn = os.path.join(dir, filename)
    mel = preprocessing.convert_to_spec_image(fn)

    # Deserialize the model
    mdl = 'covid_vgg19_trained_1.pkl'
    lrn = get_model(mdl)
    # Get predictions for the first image
    c, ind, ps = lrn.predict(mel)

    return c, ps[ind].item()

def convert_audio(path, dir, ext='wav'):
    """ Converts video to format
    corrresponding to parameter ext
    using ffmpeg """
    fn, old_ext = os.path.splitext(path)
    # Don't convert if same format
    if old_ext == ext:
        return path
    fn += '.' + ext
    src = os.path.join(dir, path)
    out = os.path.join(dir, fn)
    # -y makes sure you overwrite existing files
    cp = subprocess.run(['ffmpeg', '-y', '-i', src, out])
    if cp.returncode == 0:
        # Conversion successful!
        return fn
    else:
        return None
