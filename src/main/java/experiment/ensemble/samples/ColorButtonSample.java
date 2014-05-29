package experiment.ensemble.samples;

/**
 * Copyright (c) 2008, 2012 Oracle and/or its affiliates.
 * All rights reserved. Use is subject to license terms.
 */

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Buttons with different background colors.
 *
 * @see javafx.scene.control.Button
 * @related controls/buttons/GraphicButton
 * @related controls/buttons/HyperlinkSample
 */
public class ColorButtonSample extends Application {

    private void init(Stage primaryStage) {
        Group root = new Group();
        primaryStage.setScene(new Scene(root));
        HBox hBox = new HBox();
        hBox.setSpacing(5);
        for(int i=0; i<7; i++) {
            Button b = new Button("Color");
            b.setStyle("-fx-base: rgb("+(10*i)+","+(20*i)+","+(10*i)+");");
            hBox.getChildren().add(b);
        }
        HBox hBox2 = new HBox();
        hBox2.setSpacing(5);
        hBox2.setTranslateY(30);
        Button red = new Button("Red");
        red.setStyle("-fx-base: red;");
        Button orange = new Button("Orange");
        orange.setStyle("-fx-base: orange;");
        Button yellow = new Button("Yellow");
        yellow.setStyle("-fx-base: yellow;");
        Button green = new Button("Green");
        green.setStyle("-fx-base: green;");
        Button blue = new Button("Blue");
        blue.setStyle("-fx-base: rgb(30,170,255);");
        Button indigo = new Button("Indigo");
        indigo.setStyle("-fx-base: blue;");
        Button violet = new Button("Violet");
        violet.setStyle("-fx-base: purple;");
        hBox2.getChildren().add(red);
        hBox2.getChildren().add(orange);
        hBox2.getChildren().add(yellow);
        hBox2.getChildren().add(green);
        hBox2.getChildren().add(blue);
        hBox2.getChildren().add(indigo);
        hBox2.getChildren().add(violet);

        VBox vBox = new VBox(20);
        vBox.getChildren().addAll(hBox,hBox2);
        root.getChildren().add(vBox);
    }

    @Override public void start(Stage primaryStage) throws Exception {
        init(primaryStage);
        primaryStage.show();
    }
    public static void main(String[] args) { launch(args); }
}


