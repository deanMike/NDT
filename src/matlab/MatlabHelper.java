package matlab;

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
	
	//Constructor
	
	public MatlabHelper() throws MatlabInvocationException {
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
		
		proxy.eval("addpath(" + "'./resources/matlab/ndt.1.0.4_exported'" + ")");
		proxy.eval("add_ndt_paths_and_init_rand_generator");
		
		
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
	
	public void BinData(String dataDirectory, String dataFile, int binWidth, int stepSize) throws MatlabInvocationException {
		
		System.out.println("create_binned_data_from_raster_data(" + dataDirectory + ", " + dataFile +", " + binWidth+", " + stepSize + ")");
		proxy.eval("binned_data_file_name = create_binned_data_from_raster_data('" + dataDirectory + "', '" + dataFile +"', " + binWidth+", " + stepSize + ")");
		proxy.eval("load(binned_data_file_name);");
		proxy.eval("for i = 0:60 inds_of_sites_with_at_least_k_repeats = find_sites_with_k_label_repetitions(binned_labels.stimulus_ID, i); num_sites_with_k_repeats(i + 1) = length(inds_of_sites_with_at_least_k_repeats);end");
	}
	
	public static void main(String[] args) throws MatlabConnectionException, MatlabInvocationException{
		// create proxy
				
		 
		

		// call builtin function

		// call user-defined function (must be on the path)
		
	}
}
