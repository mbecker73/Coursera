function J = computeCost(X, y, theta)
%COMPUTECOST Compute cost for linear regression
%   J = COMPUTECOST(X, y, theta) computes the cost of using theta as the
%   parameter for linear regression to fit the data points in X and y

% Initialize some useful values
m = length(y); % number of training examples

% You need to return the following variables correctly 
J = 0;

% ====================== YOUR CODE HERE ======================
% Instructions: Compute the cost of a particular choice of theta
%               You should set J to the cost.


% X * theta compute the value of the hypothesis, then subtract value of y for training sample. 
% Then need to square the result, sum it, and reduce it by a factor of 2m
J = sum((X*theta-y).^2)/(2*m);


% =========================================================================

end
