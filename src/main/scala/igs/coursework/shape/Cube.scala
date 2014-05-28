package igs.coursework.shape

import javafx.scene.transform.Rotate
import javafx.scene.paint.Color
import javafx.scene.shape.{Rectangle, RectangleBuilder}
import scala.collection.mutable.ListBuffer
import scala.collection.JavaConversions
import igs.lab.Util
import igs.lab.shape.RotateShape
import javafx.geometry.Point3D

/**
 * @author phpusr
 *         Date: 11.04.14
 *         Time: 16:20
 */

/**
 * Куб
 */
class Cube(startPoint: Point3D, size: Double, color: Color, shade: Double) extends RotateShape {

  /** Кол-во сторон */
  private val sidesNumber = 6

  /** Цвета сторон объекта */
  private val colors = if (color != null) {
    for (i <- 1 to sidesNumber)
      yield color.deriveColor(0.0, 1.0, 1 - ((sidesNumber - i).toDouble / 10) * shade, 1.0)
  } else {
    //List(Color.ORANGE, Color.FUCHSIA, Color.GREEN, Color.RED, Color.BLUE, Color.GOLD)
    for(i <-1 to sidesNumber) yield Util.getRandomColor()
  }

  /** Список сторон объекта */
  private var sidesList: ListBuffer[Rectangle] = null

  // Инициализация объекта
  init()

  /** Меняет цвета сторон объекта */
  def changeColors() {
    sidesList.foreach { el =>
      el.setFill(Util.getRandomColor())
      el.setStrokeWidth(0)
    }
  }

  /** Построение каркасной модели */
  def wireframeModel() {
    sidesList.foreach { el =>
      el.setFill(VoidsColor)
      el.setStroke(FrameColor)
      el.setStrokeWidth(2)
    }
  }

  /** Инициализация объекта */
  private def init() {
    // back face
    var index = 0
    val r1 = RectangleBuilder.create()
    r1.width(size)
    r1.height(size)
    r1.fill(colors(index))
    r1.translateX(startPoint.getX - 0.5 * size)
    r1.translateY(startPoint.getY - 0.5 * size)
    r1.translateZ(startPoint.getZ + 0.5 * size)

    // bottom face
    index += 1
    val r2 = RectangleBuilder.create()
    r2.width(size)
    r2.height(size)
    r2.fill(colors(index))
    r2.translateX(startPoint.getX -0.5 * size)
    r2.translateY(startPoint.getY)
    r2.translateZ(startPoint.getZ)
    r2.rotationAxis(Rotate.X_AXIS)
    r2.rotate(90)

    // right face
    index += 1
    val r3 = RectangleBuilder.create()
    r3.width(size)
    r3.height(size)
    r3.fill(colors(index))
    r3.translateX(startPoint.getX - size)
    r3.translateY(startPoint.getY - 0.5 * size)
    r3.translateZ(startPoint.getZ)
    r3.rotationAxis(Rotate.Y_AXIS)
    r3.rotate(90)

    // left face
    index += 1
    val r4 = RectangleBuilder.create()
    r4.width(size)
    r4.height(size)
    r4.fill(colors(index))
    r4.translateX(startPoint.getX)
    r4.translateY(startPoint.getY - 0.5 * size)
    r4.translateZ(startPoint.getZ)
    r4.rotationAxis(Rotate.Y_AXIS)
    r4.rotate(90)

    // top face
    index += 1
    val r5 = RectangleBuilder.create()
    r5.width(size)
    r5.height(size)
    r5.fill(colors(index))
    r5.translateX(startPoint.getX - 0.5 * size)
    r5.translateY(startPoint.getY - size)
    r5.translateZ(startPoint.getZ)
    r5.rotationAxis(Rotate.X_AXIS)
    r5.rotate(90)

    // front face
    index += 1
    val r6 = RectangleBuilder.create()
    r6.width(size)
    r6.height(size)
    r6.fill(colors(index))
    r6.translateX(startPoint.getX - 0.5 * size)
    r6.translateY(startPoint.getY - 0.5 * size)
    r6.translateZ(startPoint.getZ - 0.5 * size)

    // Convert scala list => java list
    val rectangleBuilders = ListBuffer(r1, r2, r3, r4, r5, r6)
    sidesList = rectangleBuilders.map(_.build())
    val rectagles = JavaConversions.bufferAsJavaList(sidesList)

    getChildren.addAll(rectagles)
  }

}
