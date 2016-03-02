%% Script to obtain a list of all properties contained within an object and ouput to file %%

% Method to obtain list of properties %

% Hardcoded for datasource at the moment
objProp = properties(ds);
% Converts the cell array to a table then again to a csv as a cell array
% cannot be directly converted to a csv file

output_folder = strcat(toolbox_directory_name, '/../output/');

writetable(cell2table(objProp),strcat(output_folder, class(ds), '.csv'))