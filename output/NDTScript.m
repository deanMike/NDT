
%%  1.  Create listing of where the toolbox is

toolbox_directory_name = 'C:/Users/Mike/workspace/NDT/resources/matlab/ndt.1.0.4_exported'  % put name of path to the Neural Decoding Toolbox


%%  2.  Add the toolbox to Matlab's path       

addpath(toolbox_directory_name) 
add_ndt_paths_and_init_rand_generator



%%  3.  Create/Load the Binned Data  

% Mike: write these lines as commented out lines (as shown below)
%  run them once to create the Binned file, but in the final code just load the binned data...

binned_data_file_name = '';

if isempty(binned_data_file_name)
 raster_data_directory_name = 'C:/Users/Mike/workspace/NDT/resources/matlab/ndt.1.0.4_exported/Zhang_Desimone_7objects_raster_data';   % put name of path to the raster data
 save_prefix_name = 'Zhang';
 bin_width = 150; 
 step_size = 50;  
 binned_data_file_name = create_binned_data_from_raster_data(raster_data_directory_name, save_prefix_name, bin_width, step_size);
end
% Mike: the hard coded name 'Binned_Zhang_Desimone_7object_data_150ms_bins_50ms_sampled.mat' below can be obtained from the output of the create_binned_data_from_raster_data() function 

load(binned_data_file_name)





%%  4.  Create a datasource object

% Define which labels should be used for decoding
specific_binned_labels_names = 'stimulus_ID';

% Define the number of cross-validation splits to use
num_cv_splits = 20; 

% Create the datasource object
ds = basic_DS(binned_data_file_name, specific_binned_labels_names,  num_cv_splits);



% Mike: These other lines should only be written (or should replace the lines above) depending on which arguments were set in the GUI

% Mike: if the Poison Naive Bayes is used, then this should replace the line where the data source is created
% if using the Poison Naive Bayes classifier, load the data as spike counts by setting the load_data_as_spike_counts flag to 1
%ds = basic_DS(binned_data_file_name, specific_binned_labels_names,  num_cv_splits, 0);

% Mike: we can discuss these options more later - you can keep them out of the script for now (or include them as commented lines so we know to come back to them later)
% can have multiple repetitions of each label in each cross-validation split (which is a faster way to run the code that uses most of the data)
%ds.num_times_to_repeat_each_label_per_cv_split = 2;

 % optionally can specify particular sites to use
%ds.sites_to_use = find_sites_with_k_label_repetitions(the_labels_to_use, num_cv_splits);  

% can do the decoding on a subset of labels
%ds.label_names_to_use =  {'kiwi', 'flower', 'guitar', 'hand'};




%%  5.  Create a feature preprocessor object

% Create a feature preprocess that z-score normalizes each feature
the_feature_preprocessors{1} = zscore_normalize_FP;  


% Mike: again these lines should only be written if they are specified (and options will depend on which preprocessors are being used)   

% can include a feature-selection features preprocessor to only use the top k most selective neurons
% fp = select_or_exclude_top_k_features_FP;
% fp.num_features_to_use = numFeaturesToUse;   % use only the 25 most selective neurons as determined by a univariate one-way ANOVA
% the_feature_preprocessors{2} = fp;




%%  6.  Create a classifier object 

% select a classifier
the_classifier = max_correlation_coefficient_CL;


% Mike: again which lines are written will depend on the classifier (as dictated by the drop dowm menu you created) 

% use a poisson naive bayes classifier (note: the data needs to be loaded as spike counts to use this classifier)
%the_classifier = poisson_naive_bayes_CL;  

% use a support vector machine (see the documentation for all the optional parameters for this classifier)
%the_classifier = libsvm_CL;



%%  7.  Create the cross-validator 


the_cross_validator = standard_resample_CV(ds, the_classifier, the_feature_preprocessors);  

the_cross_validator.num_resample_runs = 2;  % usually more than 2 resample runs are used to get more accurate results, but to save time we are using a small number here


% Mike: again which lines are written will depend on the specific arguments set in the GUI

% can greatly speed up the run-time of the analysis by not creating a full TCT matrix (i.e., only trainging and testing the classifier on the same time bin)
% the_cross_validator.test_only_at_training_times = 1;  




%%  8.  Run the decoding analysis   

% Mike: we should discuss these lines...
% if calling the code from a script, one can log the code so that one can recreate the results 
%log_code_obj = log_code_object;
%log_code_obj.log_current_file; 


% Mike: this line should be hard coded in as is

% run the decoding analysis 
DECODING_RESULTS = the_cross_validator.run_cv_decoding; 



%%  9.  Save the results

% Mike: the save_file_name should be given by the user in the GUI (perhaps should be called save_results_name)

% save the results
save_file_name = 'Zhang_Desimone_basic_7object_results';
save(save_file_name, 'DECODING_RESULTS'); 


% Mike: we should discuss these lines...
% if logged the code that was run using a log_code_object, save the code
%LOGGED_CODE = log_code_obj.return_logged_code_structure;
%save(save_file_name, '-v7.3', 'DECODING_RESULTS', 'LOGGED_CODE'); 



