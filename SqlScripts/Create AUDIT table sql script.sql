use poc_1;
create table audit(
record_id int not null auto_increment primary key,
logfile_folder_path varchar(100),
total_files int,
name_of_files varchar(500),
date_time_of_operation varchar(45),
result varchar (45),
output_file_name varchar(45),
error_message varchar(100));
