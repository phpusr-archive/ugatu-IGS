package igs.lab

import javafx.scene.transform.Rotate
import javafx.scene.Group

/**
 * @author phpusr
 *         Date: 11.04.14
 *         Time: 20:36
 */

/**
 * Фигура, которую можно поворачивать по 3-м осям
 */
abstract class RotateShape extends Group {

  // Оси поворота объекта
  val rx = new Rotate(0,Rotate.X_AXIS)
  val ry = new Rotate(0,Rotate.Y_AXIS)
  val rz = new Rotate(0,Rotate.Z_AXIS)
  getTransforms.addAll(rz, ry, rx)

  def changeColors()

}
