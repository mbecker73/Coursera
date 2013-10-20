agecount <- function(age = NULL) {
  # argument age is integer age of victim
  # function returns the number of homicide victims of a given age
  
  ## Read "homicides.txt" data file
  homicides<-readLines("homicides.txt")
  
  #create list based on regex applies to age listed 
  matchList <- regexec("([0-9]+) years old",homicides)
  regMatches<-regmatches(homicides,matchList)
  # extract only the needed age from the regMatches list
  ages<-sapply(regMatches, function(x) x[2])
  
  ## Check that "age" is non-NULL; else throw error
  if(is.null(age)) {
    stop("Need to enter a valid age")
  }
  else {
    #get the length of ages only when the age in the list matches the argument age
    ageCount<-length(which(ages==age))
  }
  
  ## Return integer containing count of homicides for that age
  return(ageCount)
}