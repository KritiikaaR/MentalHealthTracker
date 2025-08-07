import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ConsoleUI extends Application {

    private TextArea consoleOutput;
    private TextField inputField;

    @Override
    public void start(Stage primaryStage) {
        consoleOutput = new TextArea();
        consoleOutput.setEditable(false);
        consoleOutput.setWrapText(true);

        inputField = new TextField();
        inputField.setPromptText("Type a command (add, view, exit) and press Enter");

        // Handle Enter key
        inputField.setOnAction(e -> handleCommand(inputField.getText()));

        VBox layout = new VBox(10);
        layout.getChildren().addAll(consoleOutput, inputField);

        Scene scene = new Scene(layout, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Mental Health Tracker 🧠");
        primaryStage.show();

        printToConsole("Welcome to the Mental Health Tracker 💚");
        printToConsole("Type a command: add | view | exit");
    }

    private void handleCommand(String command) {
        printToConsole("> " + command);
        inputField.clear();

        switch (command.toLowerCase()) {
            case "add":
                printToConsole("📝 [TODO] Add mood entry logic here.");
                break;
            case "view":
                printToConsole("📖 [TODO] Display mood entries here.");
                break;
            case "exit":
                printToConsole("👋 Exiting...");
                System.exit(0);
                break;
            default:
                printToConsole("❌ Unknown command. Try: add | view | exit");
        }
    }

    private void printToConsole(String message) {
        consoleOutput.appendText(message + "\n");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
