package igs.lab

import scala.util.Random
import javafx.scene.paint.Color

/**
 * @author phpusr
 *         Date: 11.04.14
 *         Time: 20:32
 */

/**
 * Утилиты
 */
object Util {

  /** Заголовки осей координат */
  object AxisTitle extends Enumeration {
    type AxisTitle = String
    val X = "X:"
    val Y = "Y:"
    val Z = "Z:"
  }

  /** Генерирует случайны цвет */
  def getRandomColor = () => {
    def randomColorNumber = () => Random.nextInt(256)
    Color.rgb(randomColorNumber(), randomColorNumber(), randomColorNumber())
  }

}
