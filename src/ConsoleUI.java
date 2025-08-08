import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class ConsoleUI extends Application {

    private TextArea consoleOutput;
    private TextField inputField;

    @Override
    public void start(Stage primaryStage) {
        consoleOutput = new TextArea();
        consoleOutput.setEditable(false);
        consoleOutput.setWrapText(true);

        inputField = new TextField();
        inputField.setPromptText("Type a command (add, view, graph, exit)");

        inputField.setOnAction(e -> handleCommand(inputField.getText()));

        VBox layout = new VBox(10, consoleOutput, inputField);
        Scene scene = new Scene(layout, 600, 400);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Mental Health Tracker");
        primaryStage.show();

        printToConsole("Welcome to the Mental Health Tracker 💚");
        printToConsole("Type a command: add | view | graph | exit");
    }

    private void handleCommand(String command) {
        printToConsole("> " + command);
        inputField.clear();

        switch (command.toLowerCase()) {
            case "add":
                addMoodEntry();
                break;
            case "view":
                displayMoodEntries();
                break;
            case "graph":
                showMoodGraph();
                break;
            case "exit":
                printToConsole("Exiting...");
                System.exit(0);
                break;
            default:
                printToConsole("Unknown command. Try: add | view | graph | exit");
        }
    }

    private void addMoodEntry() {
        TextInputDialog moodDialog = new TextInputDialog();
        moodDialog.setTitle("Add Mood Entry");
        moodDialog.setHeaderText("Enter your mood level (1–10):");
        moodDialog.setContentText("Mood level:");

        moodDialog.showAndWait().ifPresent(moodInput -> {
            try {
                int moodLevel = Integer.parseInt(moodInput.trim());
                if (moodLevel < 1 || moodLevel > 10) {
                    printToConsole("Mood level must be between 1 and 10.");
                    return;
                }

                TextInputDialog noteDialog = new TextInputDialog();
                noteDialog.setTitle("Add Note");
                noteDialog.setHeaderText("Write a short note about how you're feeling:");
                noteDialog.setContentText("Note:");

                noteDialog.showAndWait().ifPresent(noteInput -> {
                    String note = noteInput.trim();
                    String date = java.time.LocalDate.now().toString();
                    MoodEntry entry = new MoodEntry(date, moodLevel, note);
                    MoodLogger.saveEntry(entry);
                    printToConsole("Mood entry saved for " + date);
                });

            } catch (NumberFormatException e) {
                printToConsole("Invalid mood level. Please enter a number from 1 to 10.");
            }
        });
    }

    private void displayMoodEntries() {
        List<MoodEntry> entries = MoodLogger.loadEntries();
        if (entries.isEmpty()) {
            printToConsole("No mood entries found.");
        } else {
            printToConsole("Mood Log:");
            for (MoodEntry entry : entries) {
                printToConsole("-----------------------------");
                printToConsole(entry.toString());
            }
            printToConsole("-----------------------------");
            printToConsole("Total entries: " + entries.size());
        }
    }

    private void showMoodGraph() {
        List<MoodEntry> entries = MoodLogger.loadEntries();
        if (entries.isEmpty()) {
            printToConsole("📭 No data to display on graph.");
        } else {
            MoodGraph.showGraph(entries);
        }
    }

    private void printToConsole(String message) {
        consoleOutput.appendText(message + "\n");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
