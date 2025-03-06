# File converter - description

The purpose of this java application is to upload an HTML file, parse it, then convert it into CSV format.
The backend handles all the parsing and converts it to a CSV, while the front end is for user to upload the file and select a download folder for the file to be downloaded in.
The application has a simple and user-friendly graphical interface (GUI) that is constructed using JavaFx.

# Test senarios 

-The file converter will not work if the HTML file dosn't contain a second table because it only converts the information in the second table to CSV.
    + Try to upload the badTestFile.html example and it will show you and error through the user interface because the file does not have a second table to parse

-The user interface prevents the user from uploading any file that is not .html.
    + Try to upload a file that is not .html

-The user interface prevents the user from downloading the file to a folder that does not exist.
    + Try to download to a folder that does not exist

-If the user clicks the start button before uploading the file or choosing the download folder the GUI will show an error.
    + Try to run the program through the user interface without uploading a file first

-If the user enters an html file with a second table the program will convert that file into a CSV that file that can be downloaded onto their machine
    + Upload the obfuscated_course_report.html 
    + Choose a place to download it then hit start
    + Finally view the downloaded CSV file titled "outputDocument.csv"


# Dependencies

JSOUP - This is a library for easily parsing HTML files
Apache commons - This is a library to easily write files to CSV

# Instructions to run the file

1. Clone the repository from GitHub.

2. In the console run the command --> mvn clean package

3. Enter this command to run the compiled jar file --> java --module-path "$env:JAVAFX_HOME\lib" --add-modules javafx.controls,javafx.fxml -jar target/app-1.0.0.jar 

("For this command to work it needs to point to the Javafx environment variable that is on the users machine")

