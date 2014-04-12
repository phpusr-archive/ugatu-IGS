package igs.lab.shape

import javafx.scene.transform.Rotate
import javafx.scene.Group
import javafx.scene.paint.Color

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

  // Каркасная модель

  /** Цвет пустоты */
  protected val VoidsColor = Color.WHITE
  /** Цвет каркаса */
  protected val FrameColor = Color.DARKGRAY
  /** Построение каркасной модели */
  def wireframeModel()

}
