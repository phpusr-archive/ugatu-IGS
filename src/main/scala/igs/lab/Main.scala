package igs.lab

import javafx.application.{Platform, Application}
import javafx.stage.Stage
import javafx.scene.{PerspectiveCamera, Scene, Group}
import javafx.scene.layout.{StackPane, VBox}
import experiment.lab.Cube
import javafx.scene.paint.Color
import scala.swing
import javafx.embed.swing.JFXPanel
import scala.swing.{FlowPanel, MainFrame, SimpleSwingApplication}
import javafx.scene.control.Button

/**
 * @author phpusr
 *         Date: 11.04.14
 *         Time: 11:13
 */

/**
 * TODO
 */
object Main extends SimpleSwingApplication {
  // Размеры формы
  private val FormWidth = 600
  private val FormHeight = 400

  // Размеры модели
  private val ModelWidth = 400
  private val ModelHeight = 300

  /** Каркасная модель поверхности */
  private val wireframeModel = new Cube(100, Color.RED, 1)
  private val jfx = new JFXPanel

  /** Создание главной формы */
  def top = new MainFrame {
    contents = new FlowPanel {
      peer.add(jfx)
      jfx.setSize(ModelWidth, ModelHeight)
    }
  }

  /** Отложенный запуск */
  Platform.runLater(new Runnable {
    override def run() = createJavaFxComponents()
  })

  /** Создание Java FX компонентов */
  private def createJavaFxComponents() {
    val root = new Group
    val scene = new Scene(root, FormWidth, FormHeight)
    scene.setCamera(new PerspectiveCamera)
    jfx.setScene(scene)

    val vBox = new VBox(20)
    root.getChildren.add(vBox)

    val wireframeModelPanel = getWireframeModelPanel(ModelWidth, ModelHeight)
    vBox.getChildren.add(wireframeModelPanel)
  }

  /** Панель с каркасной моделью */
  private def getWireframeModelPanel(width: Int, height: Int) = {
    wireframeModel.getRx.setAngle(0)
    wireframeModel.getRy.setAngle(0)

    val stackPane = new StackPane()
    stackPane.setPrefSize(width, height)
    stackPane.getChildren.add(wireframeModel)
    
    stackPane
  }

}
