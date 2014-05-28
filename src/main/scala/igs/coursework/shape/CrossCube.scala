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

  /** Размер частей */
  private val partSize = size
  /** Размер промежутков между частями */
  private val indentSize = partSize / 3

  // Отступы
  private val sx = startPoint.getX
  private val sy = startPoint.getY
  private val sz = startPoint.getZ

  init()

  def init() {

    /** Построение креста с параметрами по умолчанию */
    val newCross = (x: Double, y: Double, z: Double) => {
      val sPoint = new Point3D(x, y, z)
      new Cross(sPoint, partSize, color, shade)
    }

    val frontCross = newCross(sx, sy, sz)
    val middle = newCross(sx, sy, sz + indentSize)
    val backCross = newCross(sx, sy, sz + indentSize * 2)
    val rightCross = newCross(-sz + indentSize * -3, sy, sx + indentSize * 2)
    rightCross.ry.setAngle(90)
    val leftCross = newCross(sz, sy, -sx - indentSize)
    leftCross.ry.setAngle(-90)

    partList = ListBuffer(middle, backCross, rightCross, leftCross, frontCross)
    getChildren.addAll(JavaConversions.bufferAsJavaList(partList))

    /** Точка вращения */
    val pivot = new Cube(new Point3D(0, 0, 0), 5, new Color(1, 0, 0, 1), shade)
    getChildren.add(pivot)
  }
}
