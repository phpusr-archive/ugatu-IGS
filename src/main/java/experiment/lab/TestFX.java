package experiment.lab;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * @author phpusr
 *         Date: 10.04.14
 *         Time: 18:49
 */
public class TestFX extends Application {

    Cube c = null;

    private Timeline animation;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        init(stage);
        stage.show();
        //play();
    }

    private void init(Stage primaryStage) {
        Group root = new Group();
        primaryStage.setResizable(true);
        primaryStage.setScene(new Scene(root, 600, 400, true));
        primaryStage.getScene().setCamera(new PerspectiveCamera());

        VBox vBox = new VBox(20);
        root.getChildren().add(vBox);

        // Каркасная модель
        StackPane modelGroup = new StackPane();
        vBox.getChildren().add(modelGroup);
        modelGroup.setPrefSize(400, 300);
        modelGroup.getTransforms().addAll( //TODO разобраться с этим
                //new Translate(400 / 2, 150 / 2)
                //,new Rotate(180, Rotate.X_AXIS)
        );
        Node content = create3dContent();
        modelGroup.getChildren().add(content);

        //TODO сделать нормальные панели, чтобы ничего не скакало

        // Управление моделью
        HBox hBox = new HBox(20);
        vBox.getChildren().add(hBox);

        Slider sliderX = new Slider(0, 360, 0);
        hBox.getChildren().add(sliderX);
        sliderX.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                System.out.println("last index: " + number + "; new index: " + number2);
                c.getRx().angleProperty().setValue(number2);
            }
        });

        Slider sliderY = new Slider(0, 360, 0);
        hBox.getChildren().add(sliderY);
        sliderY.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                System.out.println("last index: " + number + "; new index: " + number2);
                c.getRy().angleProperty().setValue(number2);
            }
        });

        Slider sliderZ = new Slider(0, 360, 0);
        hBox.getChildren().add(sliderZ);
        sliderZ.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                System.out.println("last index: " + number + "; new index: " + number2);
                c.getRz().angleProperty().setValue(number2);
            }
        });

    }

    public Node create3dContent() {
        c = new Cube(100, Color.RED,1);
        c.getRx().setAngle(0);
        c.getRy().setAngle(0);

        //c.rx.angleProperty().setValue(30);

        animation = new Timeline();
        animation.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(c.getRx().angleProperty(), 0d)
                ),
                new KeyFrame(Duration.seconds(3),
                        new KeyValue(c.getRx().angleProperty(), 360d)
                ));
        animation.setCycleCount(Animation.INDEFINITE);

        return new Group(c);
    }

    public void play() {
        animation.play();
    }

    @Override public void stop() {
        animation.pause();
    }

}
