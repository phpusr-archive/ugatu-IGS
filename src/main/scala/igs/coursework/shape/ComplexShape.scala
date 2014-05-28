package igs.coursework.shape

import igs.lab.shape.RotateShape
import scala.collection.mutable.ListBuffer

/**
 * @author phpusr
 *         Date: 28.05.14
 *         Time: 16:18
 */

/**
 * Сложная фигура - состоящая из других фигур
 */
abstract class ComplexShape extends RotateShape {

  /** Части из которых состоит фигура */
  protected var partList: ListBuffer[RotateShape] = null

  /** Построение каркасной модели */
  override def wireframeModel() = partList.foreach(_.wireframeModel())

  /** Изменение цвета */
  override def changeColors() = partList.foreach(_.changeColors())

}
