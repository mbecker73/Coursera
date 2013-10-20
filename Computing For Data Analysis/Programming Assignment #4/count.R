count <- function(cause = NULL) {
  # cause argument indicates the number of different types of homicides in the dataset
  # function reads in the homicide dataset, uses RegEx to find number of deaths by given cause 
  
  ## Read "homicides.txt" data file
  homicides <- readLines("homicides.txt")
  
  #create list based on regex applies to homicides 
  causeList<-regexec("[Cc]ause: ([^ ]+)",homicides)
  regMatches<-regmatches(homicides,causeList)
  # extract only the needed cause from the regMatches list
  regMatches<-sapply(regMatches, function(x) x[2])
  

  # check that cause is non-NULL; else throw error
  if (is.null(cause)) { 
    stop("Must enter a non-null value for cause of death")
  }
  #if valid cause then return length of the grep on that cause in the cause vector
  else if(cause=="asphyxiation") {
    countInstances <- (length(grep("[Aa]sphyxiation",regMatches)))
  }
  else if(cause=="blunt force") {
    countInstances <- (length(grep("[Bb]lunt [Ff]orce",regMatches)))
  }
  else if (cause=="other") {
    countInstances <- (length(grep("[Oo]ther",regMatches)))
  }
  else if(cause =="shooting") {
    countInstances <- (length(grep("[Ss]hooting",regMatches)))
  }
  else if(cause=="stabbing") {
    countInstances <- (length(grep("[Ss]tabbing",regMatches)))
  }
  else if (cause=="unknown") {
    countInstances <- (length(grep("[Uu]nknown",regMatches)))
  }
  else {
    stop("Not a valid cause of death")
  }
  ## Return integer containing count of homicides for that cause
  return(countInstances)
}
