package javafx;

import javafx.application.Application;
import javafx.beans.property.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.util.function.DoubleFunction;

class FunctionalSlider extends Slider {
  private ReadOnlyDoubleWrapper functionValue = new ReadOnlyDoubleWrapper();

  public FunctionalSlider(DoubleFunction<Double> function) {
    valueProperty().addListener(observable ->
        functionValue.set(
            function.apply(getValue())
        )
    );

    setLabelFormatter(new StringConverter<Double>() {
      @Override
      public String toString(Double x) {
        return String.format(
            "%1$.0f",
            function.apply(x)
        );
      }

      @Override
      public Double fromString(String s) {
        return null;
      }
    });
  }

  public double getFunctionValue() {
    return functionValue.get();
  }

  public ReadOnlyDoubleProperty functionValueProperty() {
    return functionValue.getReadOnlyProperty();
  }
}

public class FunctionalSliderSample extends Application {
    private final ListView<String> startLog = new ListView<>();
    private final ListView<String> endLog   = new ListView<>();

    @Override public void start(Stage stage) throws Exception {
        Pane logsPane = createLogsPane();
        Slider slider = createMonitoredSlider();

        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(10));
        layout.getChildren().setAll(
                slider,
                logsPane
        );
        VBox.setVgrow(logsPane, Priority.ALWAYS);

        stage.setTitle("Slider Value Change Logger");
        stage.setScene(new Scene(layout));
        stage.show();
    }

    private Slider createMonitoredSlider() {
        final FunctionalSlider slider = new FunctionalSlider(
            x -> Math.pow(2, x)
        );

        slider.setMin(0);
        slider.setValue(1);
        slider.setMax(5);
        slider.setMajorTickUnit(1);
        slider.setMinorTickCount(0);
        slider.setShowTickMarks(true);
        slider.setShowTickLabels(true);
        slider.setSnapToTicks(true);
        slider.setMinHeight(Slider.USE_PREF_SIZE);

        slider.valueChangingProperty().addListener(observable -> {
            slider.setValue(Math.round(slider.getValue()));

            String valueString = String.format(
                "%1$.2f", 
                slider.getFunctionValue()
            );

            if (slider.valueChangingProperty().get()) {
                startLog.getItems().add(
                        valueString
                );
            } else {
                endLog.getItems().add(
                        valueString
                );
            }
        });

        return slider;
    }

    private HBox createLogsPane() {
        HBox logs = new HBox(10);
        logs.getChildren().addAll(
                createLabeledLog("Start", startLog),
                createLabeledLog("End",   endLog)
        );
        return logs;
    }

    public Pane createLabeledLog(String logName, ListView<String> log) {
        Label label = new Label(logName);
        label.setLabelFor(log);

        VBox logPane = new VBox(5);
        logPane.getChildren().setAll(
                label,
                log
        );

        logPane.setAlignment(Pos.TOP_LEFT);

        return logPane;
    }

    public static void main(String[] args) { launch(args); }
}
