"""
    Use:
        inf = CoughInfer('model.pkl', 'directory)
        img = arr2image(array)
        category, image, probality = inf.predict(img) """

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

class CoughInfer():
    """ Class that carries out the inference """
    def __init__(model, dir='.'):
        """ Initialization, provide exported model file and dir """
        self.learn = load_learner(dir, model)
    
    def predict(img):
        """ Predicts from image """
        pred = self.learn.predict(img)
        # Returns tuple
        # with category, image and probabilities of each cat
        return pred

