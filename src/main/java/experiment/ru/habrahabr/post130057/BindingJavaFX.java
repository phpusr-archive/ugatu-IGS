package experiment.ru.habrahabr.post130057;

import javafx.application.Application;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Демонстрация связвания позиции кнопки с шириной формы
 * <a href="http://habrahabr.ru/post/130057/">link</a>
 */
public class BindingJavaFX extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override public void start(Stage primaryStage) {
        primaryStage.setTitle("Hello World");
        Group root = new Group();
        final Scene scene = new Scene(root, 300, 250);
        final Button btn = new Button();
        btn.setLayoutX(100);
        btn.setLayoutY(80);
        btn.setText("Hello World");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                System.out.println("Hello World");
            }
        });
        final DoubleBinding db = scene.widthProperty().subtract(150);
        db.addListener(new javafx.beans.value.ChangeListener<  Number>() {
            public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
                btn.setLayoutX(db.getValue());
            }
        });
        root.getChildren().add(btn);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}