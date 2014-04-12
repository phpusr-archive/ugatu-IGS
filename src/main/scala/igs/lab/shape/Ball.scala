package igs.lab.shape

import javafx.scene.shape.{Circle, CircleBuilder}
import javafx.scene.transform.Rotate
import scala.collection.mutable.ListBuffer
import igs.lab.Util
import javafx.scene.paint.Color

/**
 * @author phpusr
 *         Date: 11.04.14
 *         Time: 20:21
 */

/**
 * Шар
 * разделен на полосы разного цвета
 */
class Ball(size: Double) extends RotateShape {

  /** Кол-во окружностей в шаре */
  private val CircleCount = 180

  /** Список окружностей */
  private val circleList: ListBuffer[Circle] = ListBuffer()

  init()

  /** Смена цвета полос шара */
  def changeColors() {
    var color = Util.getRandomColor()
    for (i <- 0 until 180) {
      if (i % 20 == 0) color = Util.getRandomColor()
      circleList(i).setFill(color)
    }
  }

  /** Построение каркасной модели */
  override def wireframeModel() {
    for (i <- 0 until 180) {
      if (i % 20 <= 2) circleList(i).setFill(Color.DARKGRAY)
      else circleList(i).setFill(Color.WHITE)
    }
  }

  /** Создание 180 окружностей с поворотом 1 градус */
  private def init() {

    for (i <- 1 to CircleCount if i % 1 == 0) {
      val c1 = CircleBuilder.create()
      c1.fill(Util.getRandomColor())
      c1.centerX(0)
      c1.centerY(0)
      c1.rotationAxis(Rotate.X_AXIS)
      c1.rotate(i)
      c1.strokeWidth(2)
      c1.radius(size)
      val circle = c1.build()
      circleList += circle

      getChildren.add(circle)
    }
    changeColors()
  }

}
