package igs.coursework.shape

import javafx.geometry.Point3D
import javafx.scene.paint.Color
import scala.collection.mutable.ListBuffer
import scala.collection.JavaConversions

/**
 * @author phpusr
 *         Date: 28.05.14
 *         Time: 13:37
 */

/**
 * Кресто-Куб
 */
class CrossCube(startPoint: Point3D, size: Double, color: Color, shade: Double) extends ComplexShape {

  init()

  def init() {

    /** Построение креста с параметрами по умолчанию */
    val newCross = (x: Double, y: Double, z: Double) => new Cross(new Point3D(x, y, z), size, color, shade)

    val frontCross = newCross(0, 0, 0)
    val middle = newCross(0, 0, size)
    val backCross = newCross(0, 0, size * 2)
    val rightCross = newCross(size * -2, 0, size * 2)
    rightCross.ry.setAngle(90)
    val leftCross = newCross(0, 0, 0)
    leftCross.ry.setAngle(-90)

    partList = ListBuffer(middle, backCross, rightCross, leftCross, frontCross)
    getChildren.addAll(JavaConversions.bufferAsJavaList(partList))
  }
}
