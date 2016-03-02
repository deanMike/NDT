%function introduction_tutorial
%%  1.  Create strings listing where the toolbox and the tutoral data directories are
toolbox_directory_name = './resources/matlab/ndt.1.0.4_exported';  % put name of path to the Neural Decoding Toolbox
raster_data_directory_name = strcat(toolbox_directory_name, '/Zhang_Desimone_7objects_raster_data/');   % put name of path to the raster data







%%  3.  Add the toolbox to Matlab's path       

addpath(toolbox_directory_name) 
add_ndt_paths_and_init_rand_generator



%%  4.  Bin the data

save_prefix_name = 'Binned_Zhang_Desimone_7object_data';
bin_width = 150; 
step_size = 50;  

binned_data_file_name = create_binned_data_from_raster_data(raster_data_directory_name, save_prefix_name, bin_width, step_size);
 


%%  5.  Calculate how many times each stimulus has been shown to each neuron

load(binned_data_file_name);  % load the binned data

for i = 0:60
    inds_of_sites_with_at_least_k_repeats = find_sites_with_k_label_repetitions(binned_labels.stimulus_ID, i);
    num_sites_with_k_repeats(i + 1) = length(inds_of_sites_with_at_least_k_repeats);
end





%%  Begin the decoding analysis  %%




%%  6.  Create a datasource object

% we will use object identity labels to decode which object was shown (disregarding the position of the object)
specific_binned_labels_names = 'stimulus_ID';

% use 20 cross-validation splits (which means that 19 examples of each object are used for training and 1 example of each object is used for testing)
num_cv_splits = 20; 

% create the basic datasource object
ds = basic_DS(binned_data_file_name, specific_binned_labels_names,  num_cv_splits);



% other useful options:

% if using the Poison Naive Bayes classifier, load the data as spike counts by setting the load_data_as_spike_counts flag to 1
%ds = basic_DS(binned_data_file_name, specific_binned_labels_names,  num_cv_splits, 1);

% can have multiple repetitions of each label in each cross-validation split (which is a faster way to run the code that uses most of the data)
%ds.num_times_to_repeat_each_label_per_cv_split = 2;

 % optionally can specify particular sites to use
%ds.sites_to_use = find_sites_with_k_label_repetitions(the_labels_to_use, num_cv_splits);  

% can do the decoding on a subset of labels
%ds.label_names_to_use =  {'kiwi', 'flower', 'guitar', 'hand'};




%%   7.  Create a feature preprocessor object

% create a feature preprocess that z-score normalizes each feature
the_feature_preprocessors{1} = zscore_normalize_FP;  


% other useful options:   

% can include a feature-selection features preprocessor to only use the top k most selective neurons
% fp = select_or_exclude_top_k_features_FP;
% fp.num_features_to_use = 25;   % use only the 25 most selective neurons as determined by a univariate one-way ANOVA
% the_feature_preprocessors{2} = fp;




%%  8.  Create a classifier object 

% select a classifier
the_classifier = max_correlation_coefficient_CL;


% other useful options:   

% use a poisson naive bayes classifier (note: the data needs to be loaded as spike counts to use this classifier)
%the_classifier = poisson_naive_bayes_CL;  

% use a support vector machine (see the documentation for all the optional parameters for this classifier)
%the_classifier = libsvm_CL;


%%  9.  create the cross-validator 


the_cross_validator = standard_resample_CV(ds, the_classifier, the_feature_preprocessors);  

the_cross_validator.num_resample_runs = 2;  % usually more than 2 resample runs are used to get more accurate results, but to save time we are using a small number here


% other useful options:   

% can greatly speed up the run-time of the analysis by not creating a full TCT matrix (i.e., only trainging and testing the classifier on the same time bin)
% the_cross_validator.test_only_at_training_times = 1;  




%%  10.  Run the decoding analysis   

% if calling the code from a script, one can log the code so that one can recreate the results 
%log_code_obj = log_code_object;
%log_code_obj.log_current_file; 


% run the decoding analysis 
DECODING_RESULTS = the_cross_validator.run_cv_decoding; 



%%  11.  Save the results

% save the results
save_file_name = 'Zhang_Desimone_basic_7object_results';
save(save_file_name, 'DECODING_RESULTS'); 

% if logged the code that was run using a log_code_object, save the code
%LOGGED_CODE = log_code_obj.return_logged_code_structure;
%save(save_file_name, '-v7.3', 'DECODING_RESULTS', 'LOGGED_CODE'); 


%% 12 Export objects for reading into Java.

export_obj