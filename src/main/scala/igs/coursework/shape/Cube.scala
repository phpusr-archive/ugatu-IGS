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
    val randomColor = Util.getRandomColor()
    sidesList.foreach { el =>
      el.setFill(randomColor)
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

  /** Прямоугольник с настройками по умолчанию */
  private def defaultRectangleBuilder(translateX: Double, translateY: Double, translateZ: Double, colorIndex: Int) = {
    val r = RectangleBuilder.create()
    r.width(size)
    r.height(size)
    r.fill(colors(colorIndex))
    r.translateX(startPoint.getX + translateX)
    r.translateY(startPoint.getY + translateY)
    r.translateZ(startPoint.getZ + translateZ)

    r
  }

  /** Инициализация объекта */
  private def init() {
    // back face
    var index = 0
    val r1 = defaultRectangleBuilder(-0.5 * size, - 0.5 * size, 0.5 * size, index)

    // bottom face
    index += 1
    val r2 = defaultRectangleBuilder(-0.5 * size, 0, 0, index)
    r2.rotationAxis(Rotate.X_AXIS)
    r2.rotate(90)

    // right face
    index += 1
    val r3 = defaultRectangleBuilder(-size, -0.5 * size, 0, index)
    r3.rotationAxis(Rotate.Y_AXIS)
    r3.rotate(90)

    // left face
    index += 1
    val r4 = defaultRectangleBuilder(0, -0.5 * size, 0, index)
    r4.rotationAxis(Rotate.Y_AXIS)
    r4.rotate(90)

    // top face
    index += 1
    val r5 = defaultRectangleBuilder(-0.5 * size, -size, 0, index)
    r5.rotationAxis(Rotate.X_AXIS)
    r5.rotate(90)

    // front face
    index += 1
    val r6 = defaultRectangleBuilder(-0.5 * size, -0.5 * size, -0.5 * size, index)

    // Convert scala list => java list
    val rectangleBuilders = ListBuffer(r1, r2, r3, r4, r5, r6)
    sidesList = rectangleBuilders.map(_.build())
    val rectagles = JavaConversions.bufferAsJavaList(sidesList)

    getChildren.addAll(rectagles)
  }

}
