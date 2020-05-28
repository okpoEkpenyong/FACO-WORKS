from fastai.basic_train import load_learner
import preprocessing
import numpy as np
import os

def get_diagnosis(filename, dir):
    """ Takes wav file and predicts disease """
    # Preprocessing phase
    fn = os.path.join(dir, filename)
    mel = preprocessing.convert_to_spec_image(fn)

    # Deserialize the model
    mdl = 'covid_vgg19_trained_1.pkl'
    lrn = load_learner('.', mdl)
    # Get predictions for the first image
    c, ind, ps = lrn.predict(mel)

    return c, ps[ind].item()
