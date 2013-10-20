rankhospital <- function(state, outcome, num = "best") {
  # state argument indicates the two letter state abbreviation you would like to gather data for
  # outcome argument indicates either heart attack, heart failure, or pneumonia 
  # num argument can indicate either the best state, worst state, or an integer indicating the ranking 
  
  data <- read.csv("outcome-of-care-measures.csv", colClasses = "character")
  source("best.R")
  
  if(!(state %in% data$State)) {
    stop("invalid state entered")
  }
  
  ## Return hospital name in that state with the given rank 30-day death rate
  
  if(outcome == "heart attack") {
    if(num == "worst"){
      return(findWorst(11, state, data))
    }
    else {
      data[, 11] <- as.numeric(data[, 11])
      dataframe<-data.frame(table(data[["State"]]))
      datasubset<-subset(data,data[["State"]]==state)
      lengthSubset<-length(datasubset[["State"]])
      
      datasubset<-datasubset[order(datasubset[[11]],datasubset[["Hospital.Name"]]),]
      datasubset<-subset(datasubset,subset=(!is.na(datasubset[[11]])))
    }
  }
    
  else if (outcome == "heart failure") {
    if(num == "worst"){
      return(findWorst(17, state, data))
    }
    else {
      data[, 17] <- as.numeric(data[, 17])
      dataframe<-data.frame(table(data[["State"]]))
      datasubset<-subset(data,data[["State"]]==state)
      lengthSubset<-length(datasubset[["State"]])
      
      datasubset<-datasubset[order(datasubset[[17]],datasubset[["Hospital.Name"]]),]
      datasubset<-subset(datasubset,subset=(!is.na(datasubset[[17]])))
    }
  }
  
  else if(outcome == "pneumonia") {
    if(num == "worst"){
      return(findWorst(23, state, data))
    }
    else {
      data[, 23] <- as.numeric(data[, 23])
      dataframe<-data.frame(table(data[["State"]]))
      datasubset<-subset(data,data[["State"]]==state)
      lengthSubset<-length(datasubset[["State"]])
      
      datasubset<-datasubset[order(datasubset[[23]],datasubset[["Hospital.Name"]]),]
      datasubset<-subset(datasubset,subset=(!is.na(datasubset[[23]])))
    }
  }
  else {
    stop("invalid outcomem entered")
  }  

  # if num is larger than the number of hospitals in state, function returns NA
  if(is.numeric(num)&&(num>lengthSubset)) {
    return(NA)
  }
  ##if num is "best" - return best
  if(!is.numeric(num) && (num=="best")) {
    return(best(state,outcome)) #use best function 
  }

  if(is.numeric(num)) {
    return(datasubset[num,"Hospital.Name"])
  }
  
}

# helper function that finds the worst hospiral for the given outcome number
findWorst <- function(outcomeNumber, state, data) {
  data[, outcomeNumber] <- as.numeric(data[, outcomeNumber])
  data<-subset(data,data$State==state) # only need info for given state
  valMax<-max(data[[outcomeNumber]],na.rm=TRUE) #worse = maximum
  data<-subset(data,data[[outcomeNumber]]==valMax)
  data<-data[order(data[["Hospital.Name"]]),]
  return(data[1,"Hospital.Name"]) #return worst hospital name
}