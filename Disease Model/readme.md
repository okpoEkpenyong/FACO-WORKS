# Disease model and preprocessing

This subdirectory is dedicated to respiratory disease classification

- In folder "ml" there is code that trains a NN to predict disease from cough audio
- A Jupyter notebook contains the code for the model classificator. We use fastai and the VGG19 model architecture. Our model is fed with the preprocessed data that we obtained from our previous notebook 
- There's also an image of the confusion matrix from our VGG19 model
- The first model produced mediocre results, probably becaue of unbalanced categories, so we tried oversampling techniques, which improved predictions
- We also deleted category "asthma", which was extremely unbalanced
- Added new confusion matrix for the improved model
- In folder "app" there's a web app that predicts disease from wav file. You just have to record your coughing and upload your audio file in wav or mp3 format
- In "Cough examples" folder you can download cough audios in wav format to try the diagnosis prediction web app
