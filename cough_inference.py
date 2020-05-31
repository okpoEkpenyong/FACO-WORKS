"""
    Use:
        learner = get_model('model.pkl', 'directory')
        img = arr2image(spectrogram) # spec. is a numpy array
        category, index, probabilities = learner.predict(img)
        category is the category name as a string
        index is the index of the category as an integer
        probabilities is the vector with all probabilities
        probabilities[index] gives back the prob of the predicted cat
"""

from fastai.vision.image import Image
from fastai.basic_train import load_learner
import torch
import numpy as np
from torchvision.transforms import Normalize
from fastai.vision.data import imagenet_stats

def arr2image(arr, do_normalize=False, stats=imagenet_stats):
    """ Converts numpy array to fastai Image with 3 channels """
    # Drop empty dimensions
    data = np.squeeze(arr)
    # Convert to tensor
    data = torch.from_numpy(data)
    # Stack 3 copies as new channels
    data = torch.stack([data, data, data])
    # Normalize if necessary
    if do_normalize:
        nml = Normalize(stats[0], stats[1], inplace=True)
        nml(data)
    # return as image
    return Image(data)


def get_model(model, dir='.'):
    """ exported model file and dir """
    return load_learner(dir, model)

