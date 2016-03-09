package matlab;

import java.io.*;

import main.DataSource;
import matlabcontrol.*;
//////////////////////////////////////////////////////////
// The MatlabHelper class runs the code in Matlab that  //
// will create the input files for the Obj_tab class   ///
//////////////////////////////////////////////////////////
public class MatlabHelper {
	// Variables to store the path and name of the function to be run.
	private String matlabFunctionDir = "C:\\Users\\Mike\\workspace\\NDT\\resources\\matlab\\ndt.1.0.4_exported\\tutorials";		
	private String matlabFunctionName = "introduction_tutorial";
	
	private MatlabProxyFactoryOptions options;
	private MatlabProxyFactory factory;
	private MatlabProxy proxy;
	
	PrintWriter fw;
	
	private DataSource ds;
	
	//Constructor
	
	public MatlabHelper() throws MatlabInvocationException, IOException {
		
		fw = new PrintWriter("tutorial.m");
		
		this.options =
				new MatlabProxyFactoryOptions.Builder()
				.setUsePreviouslyControlledSession(true)
				.setHidden(false)
				.build();
		this.factory = new MatlabProxyFactory(options);
		try {
			this.proxy = factory.getProxy();
		} catch (MatlabConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//proxy.eval("addpath(" + "'./resources/matlab/ndt.1.0.4_exported'" + ")");
		//proxy.eval("add_ndt_paths_and_init_rand_generator");
		
		
		//proxy.eval("toolbox_directory_name = 'C:/Users/Mike/workspace/NDT/resources/matlab/ndt.1.0.4_exported'");
		//proxy.eval("raster_data_directory_name = strcat(toolbox_directory_name, '/Zhang_Desimone_7objects_raster_data/')");
		
		//proxy.feval(this.getmatlabFunctionName());
		//proxy.exit();
		// close connection
		//proxy.disconnect();
		
	}
	
	// Getters for the main function.
	public String getmatlabFunctionDir() {
		return this.matlabFunctionDir;
	}
	
	public String getmatlabFunctionName() {
		return this.matlabFunctionName;
	}
	
	public void BinData(String dataDirectory, String dataFile, int binWidth, int stepSize) throws MatlabInvocationException, IOException {
		if (this.fw == null) throw new IOException();
		PrintWriter f = this.fw;
		//System.out.println("create_binned_data_from_raster_data(" + dataDirectory + ", " + dataFile +", " + binWidth+", " + stepSize + ")");
		//proxy.eval("binned_data_file_name = create_binned_data_from_raster_data('" + dataDirectory + "', '" + dataFile +"', " + binWidth+", " + stepSize + ")");
		//proxy.eval("load(binned_data_file_name);");
		//proxy.eval("for i = 0:60 inds_of_sites_with_at_least_k_repeats = find_sites_with_k_label_repetitions(binned_labels.stimulus_ID, i); num_sites_with_k_repeats(i + 1) = length(inds_of_sites_with_at_least_k_repeats);end");
		f.println("addpath(" + "'./resources/matlab/ndt.1.0.4_exported'" + ")");
		f.println("add_ndt_paths_and_init_rand_generator");
		f.println("binned_data_file_name = create_binned_data_from_raster_data('" + dataDirectory + "', '" + dataFile +"', " + binWidth+", " + stepSize + ")");
		f.println("load(binned_data_file_name);");
		f.println("for i = 0:60 inds_of_sites_with_at_least_k_repeats = find_sites_with_k_label_repetitions(binned_labels.stimulus_ID, i); num_sites_with_k_repeats(i + 1) = length(inds_of_sites_with_at_least_k_repeats);end");
		f.println("specific_binned_labels_names = 'stimulus_ID';");

		f.println("num_cv_splits = 20;" 

+"ds = basic_DS(binned_data_file_name, specific_binned_labels_names,  num_cv_splits);"

+"the_feature_preprocessors{1} = zscore_normalize_FP;"  

+"the_classifier = max_correlation_coefficient_CL;"



+"the_cross_validator = standard_resample_CV(ds, the_classifier, the_feature_preprocessors);" 

+"the_cross_validator.num_resample_runs = 2;");


//
//
//
//% run the decoding analysis 
//DECODING_RESULTS = the_cross_validator.run_cv_decoding; 
//
//
//
//%%  11.  Save the results
//
//% save the results
//save_file_name = 'Zhang_Desimone_basic_7object_results';
//save(save_file_name, 'DECODING_RESULTS'); 
//
//% if logged the code that was run using a log_code_object, save the code
//%LOGGED_CODE = log_code_obj.return_logged_code_structure;
//%save(save_file_name, '-v7.3', 'DECODING_RESULTS', 'LOGGED_CODE'); ");
		
		f.close();
	}
	
	
	public static void main(String[] args) throws MatlabConnectionException, MatlabInvocationException{
		
		
	}
}
