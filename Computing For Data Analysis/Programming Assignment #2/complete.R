complete <- function(directory, id = 1:332) {
  # directory is a character vector indicating location of CSV data files  
  # id is an integer vector indicating the monitor ID numbers to be used  
  
  nobs1 <- numeric(0)
  for (i in id) {        
    data <- getmonitor(i, directory)   # use getmonitor function to load data     
    nobs1 <- c(nobs1, nrow(na.omit(data))) # omit NA's
  }
  
  ## Return a data frame of the form:
  ## id nobs
  ## 1  117
  ## 2  1041
  ## ...
  ## where 'id' is the monitor ID number and 'nobs' is the
  ## number of complete cases
  
  dataframe <- data.frame(id=id,nobs=nobs1) # create data frame from vectors
  return(dataframe)
  
}