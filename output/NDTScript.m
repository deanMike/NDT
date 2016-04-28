
%%  1.  Create listing of where the toolbox is

toolbox_directory_name = 'C:UsersMikeMusic'  % put name of path to the Neural Decoding Toolbox


%%  2.  Add the toolbox to Matlab's path       

addpath(toolbox_directory_name) 
add_ndt_paths_and_init_rand_generator



%%  3.  Create/Load the Binned Data  

%  run them once to create the Binned file, but in the final code just load the binned data...

binned_data_file_name = '';

if isempty(binned_data_file_name)
 raster_data_directory_name = '';   % put name of path to the raster data
 save_prefix_name = '';
 bin_width = 150; 
 step_size = 50;  
 binned_data_file_name = create_binned_data_from_raster_data(raster_data_directory_name, save_prefix_name, bin_width, step_size);
end

load(binned_data_file_name)





%%  4.  Create a datasource object

% Define which labels should be used for decoding
specific_binned_labels_names = '{';

% Define the number of cross-validation splits to use
num_cv_splits = 20; 

% Create the datasource object
ds = basic_DS(binned_data_file_name, specific_binned_labels_names,  num_cv_splits);




% if using the Poison Naive Bayes classifier, load the data as spike counts by setting the load_data_as_spike_counts flag to 1
%ds = basic_DS(binned_data_file_name, specific_binned_labels_names,  num_cv_splits, replacespikeCounts);

% can have multiple repetitions of each label in each cross-validation split (which is a faster way to run the code that uses most of the data)
%ds.num_times_to_repeat_each_label_per_cv_split = Num Times to Repeat each Label per CV Split;

 % optionally can specify particular sites to use
%ds.sites_to_use = find_sites_with_k_label_repetitions(the_labels_to_use, num_cv_splits);  

% can do the decoding on a subset of labels
%ds.label_names_to_use =  {;




%%  5.  Create a feature preprocessor object

% Create a feature preprocess that z-score normalizes each feature
the_feature_preprocessors{1} = Z-Score Normalize;  



% can include a feature-selection features preprocessor to only use the top k most selective neurons
% fp = select_or_exclude_top_k_features_FP;
% fp.num_features_to_use = replaceNum Features to Use;   % use only the 25 most selective neurons as determined by a univariate one-way ANOVA
% the_feature_preprocessors{2} = fp;




%%  6.  Create a classifier object 

% select a classifier
the_classifier = Max Correlation Coefficient;



% use a poisson naive bayes classifier (note: the data needs to be loaded as spike counts to use this classifier)
%the_classifier = poisson_naive_bayes_CL;  

% use a support vector machine (see the documentation for all the optional parameters for this classifier)
%the_classifier = libsvm_CL;



%%  7.  Create the cross-validator 


the_cross_validator = standard_resample_CV(ds, the_classifier, the_feature_preprocessors);  

the_cross_validator.num_resample_runs = 50;  % usually more than 2 resample runs are used to get more accurate results, but to save time we are using a small number here



% can greatly speed up the run-time of the analysis by not creating a full TCT matrix (i.e., only trainging and testing the classifier on the same time bin)
% the_cross_validator.test_only_at_training_times = 1;  




%%  8.  Run the decoding analysis   

% if calling the code from a script, one can log the code so that one can recreate the results 
%log_code_obj = log_code_object;
%log_code_obj.log_current_file; 



% run the decoding analysis 
DECODING_RESULTS = the_cross_validator.run_cv_decoding; 



%%  9.  Save the results


% save the results
save_file_name = '';
save(save_file_name, 'DECODING_RESULTS'); 


% if logged the code that was run using a log_code_object, save the code
%LOGGED_CODE = log_code_obj.return_logged_code_structure;
%save(save_file_name, '-v7.3', 'DECODING_RESULTS', 'LOGGED_CODE'); 



