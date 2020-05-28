# Disease model and preprocessing

This subdirectory is dedicated to respiratory disease classification. It contains:

- Jupyter notebook for audio preprocessing which include melespectograms conversion of cough data
- Jupyter notebook with the code for the model classificator. We use fastai and the VGG19 model architecture. Our model is fed with the preprocessed data that we obtained from our previous notebook 
- An image of the confusion matrix from our VGG19 model
- The first model produced mediocre results, probably becaue of unbalanced categories, so we tried oversampling techniques, which improved predictions
- The new confusion matrix lives at 'oversampling.png'
- Added confusion matrix implementing mixup and oversampling methods and deleting extremely unbalanced category 'Asthma'. Mixupover image 
