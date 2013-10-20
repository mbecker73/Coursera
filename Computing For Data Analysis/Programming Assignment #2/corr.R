corr <- function(directory, threshold = 0) {
  # directory is a character vector indicating location of CSV data files  
  # threshold is a numeric vector of length 1 indicating the number of completely observed
  # observations required to compute the correlation; defaults to zero 
  
  
  nobs1 <- numeric(0)
  correlations <- numeric(0)
  for (i in 1:332) {        
    data <- getmonitor(i, directory)        
    nobs1 <- c(nobs1, nrow(na.omit(data)))
    
    if(nobs1[i] >= threshold) { # only compute correlations for number of nobs over threshold
      data <- data[complete.cases(data),] # only uses vectors without missing data
      correlations <- c(correlations, cor(data[, "sulfate"], data[, "nitrate"]))
    } 
    
  }
  
 return(correlations) # returns a numerid vector of the correlations
}