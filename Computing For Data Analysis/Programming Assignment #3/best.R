best <- function(state, outcome) {
  # state argument indicates the two letter state abbreviation you would like to gather data for
  # outcome argument indicates either heart attack, heart failure, or pneumonia 
  # function returns the hospital name with the best (lowest) 30-day mortality rate 
  # for the specified outcome in the given state
  
  data <- read.csv("outcome-of-care-measures.csv", colClasses = "character")
  options(warn=-1) # sets R warnings off for NA data
  
  # make sure state entered is valid
  if(!(state %in% data$State)) { 
    stop("invalid state entered")
  }
  
  # check that outcome is valid and do appropriate calculations
  # found outcome numbers using provided informational PDF
  if(outcome == "heart attack") {
    findBest(11, state, data)
  }
  else if (outcome == "heart failure") {
    findBest(17, state, data)
  }
  else if(outcome == "pneumonia") {
    findBest(23, state, data)
  }
  else {
    stop("invalid outcome entered")
  }  
  
}

# helper function that finds the best hospiral for the given outcome number
findBest <- function(outcomeNumber, state, data) {
  data[, outcomeNumber] <- as.numeric(data[, outcomeNumber])
  data<-subset(data,data$State==state) # only need info for given state
  valMin<-min(data[[outcomeNumber]],na.rm=TRUE) # best in this case = minimum
  data<-subset(data,data[[outcomeNumber]]==valMin)
  data<-data[order(data[["Hospital.Name"]]),]
  return(data[1,"Hospital.Name"]) # return best hospital name
}