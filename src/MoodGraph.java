import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class MoodGraph {

    public static void showGraph(List<MoodEntry> entries) {
        Stage stage = new Stage();
        stage.setTitle("Mood Trend ");

        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis(1, 10, 1);
        xAxis.setLabel("Date");
        yAxis.setLabel("Mood Level");

        LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Mood Trend Over Time");

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Mood Level");

        for (MoodEntry entry : entries) {
            series.getData().add(new XYChart.Data<>(entry.getDate(), entry.getMoodLevel()));
        }

        lineChart.getData().add(series);

        VBox vbox = new VBox(lineChart);
        Scene scene = new Scene(vbox, 600, 400);
        stage.setScene(scene);
        stage.show();
    }
}
