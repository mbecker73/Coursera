getmonitor <- function(id, directory, summarize = FALSE) {
  # id is an integer vector of length 1 indicating the monitor ID number
  # directory is a character vector indicating location of CSV data files  
  # summarize is a boolean indicating whether a summary of the data will be printed or 
  # just the data will be printed; defaults to FALSE
  
  fileid <- toString(id)
  #do error checking on file id and convert to proper character filename representation 
  if(nchar(fileid) == 1){
    fileid <- paste("00",fileid,sep="") #concatenate vectors by separator
  }
  else if (nchar(fileid) == 2){
    fileid <- paste("0",fileid,sep="")
  }
  else if (fileid > 332 || fileid < 0){
    stop("Must enter a file id between 0 and 332")
  }
  
  filename <- sprintf("%s%s%s%s",directory,"/",fileid,".csv")
  #create full directory path and read in file 
  data <- read.csv( filename ) 
  
  if (summarize == TRUE){   
    print(summary(data))
  }
  return(data)
  
}