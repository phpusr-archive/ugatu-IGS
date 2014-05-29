package experiment.lab;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.RectangleBuilder;
import javafx.scene.transform.Rotate;

/**
 * @author phpusr
 *         Date: 11.04.14
 *         Time: 10:48
 */

public class Cube extends Group {
    private final Rotate rx = new Rotate(0,Rotate.X_AXIS);
    private final Rotate ry = new Rotate(0,Rotate.Y_AXIS);
    private final Rotate rz = new Rotate(0,Rotate.Z_AXIS);


    public Cube(double size, Color color, double shade) {


        getTransforms().addAll(rz, ry, rx);
        getChildren().addAll(
                RectangleBuilder.create() // back face
                        .width(size).height(size)
                        .fill(color.deriveColor(0.0, 1.0, (1 - 0.5*shade), 1.0))
                        .translateX(-0.5*size)
                        .translateY(-0.5*size)
                        .translateZ(0.5*size)
                        .build(),
                RectangleBuilder.create() // bottom face
                        .width(size).height(size)
                        .fill(Color.GREEN.deriveColor(0.0, 1.0, (1 - 0.4*shade), 1.0))
                        .translateX(-0.5*size)
                        .translateY(0)
                        .rotationAxis(Rotate.X_AXIS)
                        .rotate(90)
                        .build(),
                RectangleBuilder.create() // right face
                        .width(size).height(size)
                        .fill(color.deriveColor(0.0, 1.0, (1 - 0.3*shade), 1.0))
                        .translateX(-1*size)
                        .translateY(-0.5*size)
                        .rotationAxis(Rotate.Y_AXIS)
                        .rotate(90)
                        .build(),
                RectangleBuilder.create() // left face
                        .width(size).height(size)
                        .fill(color.deriveColor(0.0, 1.0, (1 - 0.2*shade), 1.0))
                        .translateX(0)
                        .translateY(-0.5*size)
                        .rotationAxis(Rotate.Y_AXIS)
                        .rotate(90)
                        .build(),
                RectangleBuilder.create() // top face
                        .width(size).height(size)
                        .fill(color.deriveColor(0.0, 1.0, (1 - 0.1*shade), 1.0))
                        .translateX(-0.5*size)
                        .translateY(-1*size)
                        .rotationAxis(Rotate.X_AXIS)
                        .rotate(90)
                        .build(),
                RectangleBuilder.create() // top face
                        .width(size).height(size)
                        .fill(color)
                        .translateX(-0.5*size)
                        .translateY(-0.5*size)
                        .translateZ(-0.5*size)
                        .build()
        );
    }

    public Rotate getRx() {
        return rx;
    }

    public Rotate getRy() {
        return ry;
    }

    public Rotate getRz() {
        return rz;
    }
}
