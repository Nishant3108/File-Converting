# File converter - description

The purpose of this java application is to upload a HTML file parse it then and convert it into CSV format.
The backend handles all the parsing and converting it to a CSV, while the front end is for user to upload the file and select a download folder for the file to be downloaded in.
The application has a simple and user-friendly graphical interface (GUI) that is constructed using JavaFx.

# Test senarios 

The file converter will not work if the HTML file dosn't contain a second tables (<table> or <td> or <tr> should be present in the file so that it is converted)
If this is the case, the GUI will try converting it but will show an error.
The user will only be able to upload a .html file in the user interface.
The file will not be downloaded if the given folder does not exist.
If the user clicks the start button before uploading the file or choosing the download folder the GUI will show an error.

# Dependencies

JSOUP - This is for parsing HTML files
Apache commons - This is for writing to CSV

# Instructions to run the file

1. Clone or download the file from GitHub.
2. First in the console run this command --> mvn clean package
3. Then use this code to run it in the console --> java --module-path "$env:JAVAFX_HOME\lib" --add-modules javafx.controls,javafx.fxml -jar target/app-1.0.0.jar
4. Make sure the env is pointed to your JavaFx
