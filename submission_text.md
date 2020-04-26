## INSPIRATION
A diagnosis of respiratory disease is one of the most common outcomes of visiting a doctor. Respiratory diseases can be caused by inflammation, bacterial infection or viral infection of the respiratory tract. Diseases caused by inflammation include chronic conditions such as asthma, cystic fibrosis, COVID-19 and chronic obstructive pulmonary disease (COPD). Acute conditions, caused by either bacterial or viral infection, can affect either the upper or lower respiratory tract. Upper respiratory tract infections include common colds while lower respiratory tract infections include diseases such as pneumonia. Other infections include influenza, acute bronchitis, and bronchiolitis. Typically, doctors use stethoscopes to listen to the lungs as the first indication of a respiratory problem. The information available from these sounds is compromised as the sound has to first pass through the chest musculature which muffles high-pitched components of respiratory sounds. In contrast, the lungs are directly connected to the atmosphere during respiratory events such as coughs.

These audible sounds, used by our app, contain significantly more information than the sounds picked up by a stethoscope. Our approach is automated and removes the need for human interpretation of respiratory sounds. Plus, we can see lots of spreadable diseases nowadays such as HIV, Coronavirus, etc., so we have to track those patients to stop them from spreading the disease.

##PROBLEM STATEMENT
<ol>
<li>In this difficult time, a lot of people panic if they have signs of any of the symptoms, and they want to visit the doctor.</li>
<li>It isn’t necessary for the patients to always visit the doctor, as they might have a normal fever, cold or other condition that does not require immediate medical care. </li>
<li>The patient who might not have COVID-19 might contract the disease during his visit to the Corona testing booth, or expose others if they are infected. </li>
<li>Most of the diseases related to the respiratory systems can be assessed by the use of a stethoscope, which requires the patient to be physically present with the doctor.</li>
<li>Healthcare access is limited—doctors can only see so many people, and people living in rural areas may have to travel to seek care, potentially exposing others and themselves.</li>

## SOLUTION
We provide a point of care diagnostic solutions for telehealth that are easily integrated into existing platforms. We are working on an app to provide instant clinical quality diagnostic tests and management tools directly to consumers and healthcare providers. Our app is based on the premise that cough and breathing sounds carry vital information on the state of the respiratory tract. It is created to diagnose and measure the severity of a wide range of chronic and acute diseases such as corona, pneumonia, asthma, bronchiolitis and chronic obstructive pulmonary disease (COPD) using this insight. These audible sounds, used by our app, contain significantly more information than the sounds picked up by a stethoscope. app approach is automated and removes the need for human interpretation of respiratory sounds, plus user disease can also be detected by measuring heart beat from camera of smartphone.

**The application works in the following manner:**
<ul>
<li>User downloads the application from the app store and registers himself/herself.</li>
<li>After creating his/her account, they have to go through a questionnaire describing their symptoms like headache, fever, cough, cold etc. </li>
<li>After the questionnaire, the app records the users’ coughing, speaking ,breathing and heart rate in form  of video from smartphone. </li>
<li>After recording, the integrated AI system will analyze the sound recording,heart rate comparing it with a large database of respiratory sounds.  If it detects any specific pattern inherent to a particular disease in the recording, it will enable the patient to contact a nearby specialist doctor. </li>
<li>The doctor then receives a notification on a counterpart of this app, for doctors. The doctor can view the form, watch the audio recording, and also read the report given by the AI of the application. </li>
<li>The doctor, depending upon the report of the AI, will develop a diagnosis, suggest medicines, or recommend a hospital visit if the person shows symptoms of corona  or other serious condition.</li>
<li>In cases where the AI detects a very seriously ill patient, it will also enable the physician to call an ambulance to the users’ location and continuously track the user.</li>

## HOW WE ARE GOING TO BUILD IT
We will take a machine learning approach to develop highly-accurate algorithms that diagnose disease from cough and respiratory sounds. Machine learning is an artificial intelligence technique that constructs algorithms with the ability to learn from data. In our approach, signatures that characterize the respiratory tract are extracted from cough and breathing sounds. We start by matching signatures in a large database of sound recordings with known clinical diagnoses. Our machine learning tools then find the optimum combination of these signatures to create an accurate diagnostic test or severity measure (this is called classification). Importantly, we believe these signatures are consistent across the population and not specific to an individual so there is no need for a personalized database Following are the steps the app will take: 
<ol>
<li>Receive an audio signal from the user's phone microphone </li> 
<li> Filter the signal so as to improve its quality and remove background noise
<li> Run the signal through an artificial neural network which will decide whether it is an usable breathing or cough signal
<li> Convert the signal into a frequency-based representation (spectrogram)</li>
 <li> Run the signal through a conveniently trained artificial neural network that would predict the user's condition and possible illness.</li>
<li> Store features of the audio signal when the classification indicates a symptom</li>

For tracking patients, these technologies will be used-:: Client (Frontend): React (JavaScript + Material-UI) Server (Backend): Blockstack managed backed (user data), Golang (notifications, education) Database: Gaia managed storage (user data), MongoDB (notifications, education) Deployment: Client site deployed on AWS S3 Golang servers deployed on AWS EC2 behind an API Gateway Blockstack/Gaia is managed by Blockstack, not us then through tools, API, and languages such as Java, XML,webrtc (shown in the last pic), google maps, etc, we going to make rest of the functionality of the app.
We use a variety of Python libraries to help up filter the signal and convert it to a spectrogram. For classification and prediction we set up and traindeep learning artificial network using the fastai library.

##IMPACT
FACO will help patients get themselves tested at home, supporting in areas where tests and access to tests are limited. This will help democratize care in hard-to-reach or resource-strapped areas, and provide peace of mind so that patients will not overwhelm already stressed healthcare systems. Doctors will be able to prioritize patients with an urgent need related to their speciality, providing care from the palm of their hand, limiting their exposure and travel time. 

## Challenges we ran into
<ul>
<li>
No financial support.</li>
<li>Working under quarantine measures.</li>
<li>Working in different time-zones.</li>
<li>Scarcity of high-quality data sets to train our models with</li>
<li>One Feature Related Problem- Legal shortcomings we might face when adding the tracking patient feature</li>

## Accomplishments that We're proud of
We went from initial concept to a full working prototype. We got a jumpstart on organizational strategy, revenue and business plans—laying the groundwork for building partnerships with healthcare providers and pharmacies. On the creative side, we built our foundational brand and design system, and created over 40 screens to develop a fully working prototype of our digital experience. Our prototype models nearly the entire app experience—from recording respiratory sounds to reporting to managing contact, care, and prescriptions with physicians.  Technologically, we successfully developed an algorithm for disease and have begun the application development process—well on our way to making this a fully functional product within the next 20 days.

You can explore the [full prototype here](https://www.figma.com/proto/8fi3GyTtFC0BC4KrVjhY22/FACO?node-id=27%3A52&scaling=scale-down)or [watch the demo ](  https://drive.google.com/file/d/1HwJ5KoreYPgVm4flypOxnJIFpcPLQyWN/view?usp=drivesdk)(and [check out our promo gif](    https://drive.google.com/file/d/10ZCh0J24qVo3GWIiwGHpUBBMQtc2UG9o/view?usp=drivesdk))! 

### WHAT WE'VE DONE OVER THIS WEEKEND
We wanted to show that the project is feasible. Scientific literature has shown that audio data can help diagnose respiratory diseases. We provide some references below. However, it is unclear how reliable such a model would be in real situations.
For that reason, we used a publicly available annotated [dataset](https://dataverse.harvard.edu/dataset.xhtml?persistentId=doi:10.7910/DVN/YDEPUT) of cough samples:
It is a collection of audio files in wav format classified into four different categories.
We wrote code in Python that converts those samples into MEL spectrograms. For the time being we are not using the MEL scale, just the sprectograms. We did several kinds of pre-processing of the signals, including data augmentation, then convert all pre-processed signals, along with their categories into a _databunch_ object that can be used for training artificial neural networks created in the fastai library. The signals within the databunch were divided into training and validation sets.
Because the dataset size was reduced, we used _transfer learning_. That is, we used previously trained networks as a starting point, rather than training from scratch. We treated the spectrograms as if it were images and used powerful models pretrained to classify images from large datasets. In particular, we tried both two variants of _resnet_ and two variants of _VGG_ differing on their depth (number of hidden layers). This approach implied turning the sprectograms into image-like representations and normalizing them according to the statistics of the original dataset our models were trained on (imagenet). We first changed the head of the networks to one that would classify according to our categories and trained only that part of the net, _freezing_ the rest. Later on we _unfroze_ the rest of the net and further trained it. We finally compared the different models by the confusion matrices that we obtained from the validation test. We finally settled on a model based on _VGG19_. We exported the model for later use in classifying audio samples through the pre-existing interface of our mobile app.
The results are promising, especially considering the small amount of data that we have available at this moment. We have included an image of the final confusion matrix that shows how our current network can correctly classify all four categories of signal about 50% of the time, far better than the random level of 25%. We conclude that wav files obtained trough a phone mic provide information that can be useful for diagnosing respiratory condition. We are confident that we can vastly improve both the sensitivity and the specificity of our model if we can gain access to larger, more representative datasets.
We provide an image of the final confusion matrix for our model.

## What's next for FACO-Fight Against Corona (and what we need)
Our goals are global - we aim to produce and distribute our project around the world as quickly as possible! From the technological level, we now need to make the algorithm more accurate, also work intensively on the application development process, finish it and finally launch. Besides that, it is necessary to connect with public health providers of targeted areas and establish partnerships, build a marketing and PR company.
The next step for us is to create a high-quality extensive database of audio samples and train adequate predictive models with those samples. We would need resources for obtaining, storing and curating respiratory samples from a variety of patients from different backgrounds and suffering a variety of respiratory disorders, besides control subjects.
As mentioned above, collaboration with hospitals and other health providers would be key in the process of creating such a database.
We also need equipment with better computing power in order to train very deep models and create a pipeline that can classify conditions accurately. 

## References
Porter P, Claxton S, Wood J, Peltonen V, Brisbane J, Purdie F, Smith C, Bear N, Abeyratne U,
[Diagnosis of Chronic Obstructive Pulmonary Disease (COPD) Exacerbations Using a Smartphone-Based, Cough Centred Algorithm, ERS 2019, October 1, 2019.](http://ers-eposter.key4events.com/145/71370.pdf)


Porter P, Abeyratne U, Swarnkar V, Tan J, Ng T, Brisbane JM, Speldewinde D, Choveaux J, Sharan R, Kosasih K and Della, P, 
[A prospective multicentre study testing the diagnostic accuracy of an automated cough sound centered analytic system for the identification of common respiratory disorders in children,](https://respiratory-research.biomedcentral.com/articles/10.1186/s12931-019-1046-6)
 Respiratory Research 20(81), 2019

Moschovis PP, Sampayo EM, Porter P, Abeyratne U, Doros G, Swarnkar V, Sharan R, Carl JC,
[A Cough Analysis Smartphone Application for Diagnosis of Acute Respiratory Illnesses in Children, ATS 2019, May 19, 2019.](https://www.abstractsonline.com/pp8/#!/5789/presentation/11880)
 

Sharan RV, Abeyratne UR, Swarnkar VR, Porter P, 
[Automatic croup diagnosis using cough sound recognition, IEEE Transactions on Biomedical Engineering 66(2), 2019.](https://doi.org/10.1109/TBME.2018.2849502)


Kosasih K, Abeyratne UR,
[Exhaustive mathematical analysis of simple clinical measurements for childhood pneumonia diagnosis, World Journal of Pediatrics 13(5), 2017.](https://doi.org/10.1007/s12519-017-0019-4)
 

Kosasih K, Abeyratne UR, Swarnkar V, Triasih R, [Wavelet augmented cough analysis for rapid childhood pneumonia diagnosis, IEEE Transactions on Biomedical Engineering 62(4), 2015.](https://doi.org/10.1109/TBME.2018.2849502)

Amrulloh YA, Abeyratne UR, Swarnkar V, Triasih R, Setyati A, [Automatic cough segmentation from non-contact sound recordings in pediatric wards, Biomedical Signal Processing and Control 21, 2015.](http://dx.doi.org/10.1016/j.bspc.2015.05.001)

Swarnkar V, Abeyratne UR, Chang AB, Amrulloh YA, Setyati A, Triasih R,[ Automatic identification of wet and dry cough in pediatric patients with respiratory diseases, Annals Biomedical Engineering 41(5), 2013.](http://dx.doi.org/10.1007/s10439-013-0741-6)

Abeyratne UR, Swarnkar V, Setyati A, Triasih R, [Cough sound analysis can rapidly diagnose childhood pneumonia, Annals Biomedical Engineering 41(11), 2013.](http://dx.doi.org/10.1007/s10439-013-0836-0)
