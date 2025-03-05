package com.example;

import java.io.File; //handle file exception
import javafx.application.Application; //for java application
import javafx.event.ActionEvent;//for user actions
import javafx.event.EventHandler;//handles events in JavaFX
import javafx.geometry.Insets;
import javafx.geometry.Pos;//for positioning
import javafx.scene.Scene;//for content inside the window
import javafx.scene.control.Alert;//displays alert pop-ups for errors or notification
import javafx.scene.control.Button;//for clickable buttons
import javafx.scene.control.ComboBox;//drowdown menu to select file type
import javafx.scene.control.Label;//display text
import javafx.scene.layout.HBox;//horizontal layout
import javafx.scene.layout.VBox;//vertical layout
import javafx.stage.DirectoryChooser;//directory chooser
import javafx.stage.FileChooser;//file chooser
import javafx.stage.Stage;//application window

//UI class for javaFX application - buttons, upload file, download method
public class UI extends Application {

    // private Desktop desktop = Desktop.getDesktop(); // provides access to
    // ldesktop
    // features like selecting files

    private File uploadedFile; // To store the uploaded file
    private File downloadFolder; // To store the selected download folder

    @Override
    public void start(Stage primaryStage) {
        // Set the title of the window
        primaryStage.setTitle("IVY TECH File Converter");

        // ---------------------- TITLE BAR (TOP MOST) ----------------------//

        // Create an HBox for the top green strip
        HBox header = new HBox();
        header.setPrefHeight(50); // Set height of the strip
        header.setAlignment(Pos.CENTER); // Center the content
        header.setStyle("-fx-background-color: green;"); // Set background color of header

        // Create the IVY TECH label
        Label titleLabel = new Label("IVY TECH - File Converter");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: white;");

        // Add label to the header
        header.getChildren().add(titleLabel);

        // ---------------------- MAIN LAYOUT ----------------------//

        // Create a vertical layout to organize components
        VBox layout = new VBox(20); // 20 is the spacing between components
        layout.setPadding(new Insets(20)); // Add padding around the layout
        layout.setAlignment(Pos.CENTER); // center align elements

        // Add header to the layout at the top
        layout.getChildren().add(header);

        // ---------------------- IMPORT FILE SECTION ----------------------//

        // Create a label for the "Import file" section
        Label importLabel = new Label("Import file");
        importLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        layout.getChildren().add(importLabel);

        // Create a dropdown menu for file type selection
        ComboBox<String> fileTypeDropdown = new ComboBox<>();
        fileTypeDropdown.getItems().addAll("HTML"); // Add file types
        fileTypeDropdown.setPromptText("File Type"); // Set placeholder text
        fileTypeDropdown.setPrefWidth(350);// width of dropdown
        layout.getChildren().add(fileTypeDropdown);

        // Create a FileChooser
        final FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select a File to Upload");// title of file chooser window

        // Set a file extension filter to only accept .html files
        FileChooser.ExtensionFilter htmlFilter = new FileChooser.ExtensionFilter("HTML Files (*.html)", "*.html");
        fileChooser.getExtensionFilters().add(htmlFilter);

        // Label to display the upload file path
        Label uploadPathLabel = new Label("No file selected");
        uploadPathLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: gray;");
        layout.getChildren().add(uploadPathLabel);

        // Create an "Upload file" button
        final Button uploadButton = new Button("Upload file");
        uploadButton.setStyle("-fx-font-size: 14px;"); // Set button font size
        uploadButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent e) {
                // final keyword used so that variable e is not reassigned inside the method
                // Check if the user selected a file format from dropdown
                String selectedFileType = fileTypeDropdown.getValue();
                if (selectedFileType == null) {

                    // Note: The showAlert method is declared at the bottom of the code to handle
                    // alert pop-ups.

                    // warning alert that file format was selected above
                    showAlert(Alert.AlertType.WARNING, "Warning", "Error: File format not selected.");
                    return;
                }
                // Open the file chooser dialog
                File file = fileChooser.showOpenDialog(primaryStage);
                if (file != null) {
                    uploadedFile = file;
                    // Check if the selected file has a .html extension
                    if (file.getName().toLowerCase().endsWith(".html")) {
                        // Updating label to show selected file name
                        uploadPathLabel.setText("File has been selected - " + file.getName()); // Update
                    } else {
                        // Showing error alert if the file is not an HTML file
                        showAlert(Alert.AlertType.ERROR, "Invalid File", "Please select a valid .html file.");
                    }
                }
            }
        });
        layout.getChildren().add(uploadButton);

        // ---------------------- DOWNLOAD FILE SECTION ----------------------//

        // Create a label for the "Download file" section
        Label downloadLabel = new Label("Download file");
        downloadLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        layout.getChildren().add(downloadLabel);

        // Create a "Select Download Folder" button
        Button selectFolderButton = new Button("Select Download Folder");
        selectFolderButton.setStyle("-fx-font-size: 14px;");

        // Create a label to display the selected download folder path
        Label folderPathLabel = new Label("No folder selected");
        folderPathLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: gray;");
        layout.getChildren().add(folderPathLabel);

        selectFolderButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                // Open a directory chooser dialog
                DirectoryChooser directoryChooser = new DirectoryChooser();
                directoryChooser.setTitle("Select Download Folder");
                File folder = directoryChooser.showDialog(primaryStage);
                if (folder != null) {
                    downloadFolder = folder; // Store the selected folder
                    folderPathLabel.setText("Folder has been selected - " + folder.getAbsolutePath()); // Update label
                    // showing confirmation alert when folder is selected
                    showAlert(Alert.AlertType.INFORMATION, "Folder Selected",
                            "Download folder selected: " + folder.getAbsolutePath());
                }
            }
        });
        layout.getChildren().add(selectFolderButton);

        // ---------------------- START BUTTON ----------------------//

        // Create a "Start" button
        Button startButton = new Button("Start");
        startButton.setStyle("-fx-font-size: 16px;");
        startButton.setOnAction(e -> {
            if (uploadedFile != null && downloadFolder != null) {
                // Create an instance of Main FileProcessor
                Main fileProcessor = new Main();

                // Process the main
                fileProcessor.FileProcessor(uploadedFile, downloadFolder);

                // Shows information alert when processing starts
                showAlert(Alert.AlertType.INFORMATION, "Final Message",
                        "Processing.....\nYour file has been downloaded to: " + downloadFolder.getAbsolutePath());
            } else {
                // Shows error alert if file or folder is missing
                showAlert(Alert.AlertType.ERROR, "Missing Selection", "Missing file or download folder path.");
            }
        });
        layout.getChildren().add(startButton);

        // ---------------------- SCENE ----------------------//

        // Create a scene with the layout and set its size
        Scene scene = new Scene(layout, 1000, 500);

        // Set the scene to the stage (window)
        primaryStage.setScene(scene);

        // Show the stage (window)
        primaryStage.show();
    }

    // method to show alerts
    // this was done becuase alert is repeated multiple times in the code
    // this make the code readable and cleaner
    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
