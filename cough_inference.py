"""
    Use:
        learner = get_model('model.pkl', 'directory')
        img = arr2image(spectrogram) # spec. is a numpy array
        category, _, probabilities = learner.predict(img)
        # The probability of the prediction is the maximum
        of the probabilities vector """

from fastai.vision.image import Image
from fastai.basic_train import load_learner
import torch
import numpy as np

def arr2image(arr):
    """ Converts numpy array to fastai Image with 3 channels """
    # Drop empty dimensions
    data = np.squeeze(arr)
    # Convert to tensor
    data = torch.from_numpy(data)
    # Stack 3 copies as new channels
    data = torch.stack([data, data, data])
    # return as image
    return Image(data)


def get_model(model, dir='.'):
    """ exported model file and dir """
    return load_learner(dir, model)

