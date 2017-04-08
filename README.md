# onlineTest
Online Multiple Test Web Application 

INSTRUCTIONS TO RUN
################################################################################

Run through eclipse using maven tomcat7 plugin:

=## In eclipse maven build configuration: ##=

To build:
	clean package

To run:
	tomcat:run

=## To deploy war directly to tomcat ##= 
	update db.properties in WEB-INF/Classess/ to specify path to sqlite db named test.db

################################################################################
OVERVIEW OF ARCHITECHTURE
################################################################################

1. Welcome page accepts NAME and EMAIL-ID
	- A REST API : /onlineTest/user/getUser/hjadhav@usc.edu/Heramb%20Jadhav
is called which checks if email-id already exists and if that user has previously given tests. If it's a new User, a record is created in the USERS table. 
The review button is disbaled for a new user or a user without any previous test data. it is enabled only for users who has given previous tests.

2. User can Select a TOPIC and TEST of his choice. When he clicks on the 'Start Test' button, a REST API is called : /onlineTest/services/Economics/Test1/getQuestions
This API maps and returns the requested QUESTION set from a JSON file.

3. When the user answers all options and clicks on 'Submit', A REST API is called to save user's data. Also, an ALERT is generated which mentions your score. User's answers are posted to :/onlineTest/user/saveUserData

4. Clicking on review invokes a REST API: /onlineTest/user/hjadhav@usc.edu/reviewData 
which returns a JSON response with all the previous user Test data.
