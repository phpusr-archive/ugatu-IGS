package igs.lab

import javafx.application.{Platform, Application}
import javafx.stage.Stage
import javafx.scene.{PerspectiveCamera, Scene, Group}
import javafx.scene.layout.{HBox, StackPane, VBox}
import javafx.scene.paint.Color
import scala.swing
import javafx.embed.swing.JFXPanel
import scala.swing.{FlowPanel, MainFrame, SimpleSwingApplication}
import javafx.scene.control.{Label, Slider, Button}
import javafx.beans.value.{ObservableValue, ChangeListener}
import javafx.scene.transform.Rotate
import javafx.event.{ActionEvent, EventHandler}
import javafx.geometry.{Insets, Pos}
import javafx.scene.shape.Polygon

/**
 * @author phpusr
 *         Date: 11.04.14
 *         Time: 11:13
 */

/**
 * TODO каркасная модель
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
    centerOnScreen()
  }

  /** Отложенный запуск */
  Platform.runLater(new Runnable {
    override def run() = createJavaFxComponents()
  })

  /** Создание Java FX компонентов */
  private def createJavaFxComponents() {
    val root = new VBox(20)
    val scene = new Scene(root, FormWidth, FormHeight)
    scene.setCamera(new PerspectiveCamera)
    jfx.setScene(scene)

    // Каркасная модель
    val wireframeModelPanel = getWireframeModelPanel(ModelWidth, ModelHeight)
    root.getChildren.add(wireframeModelPanel)

    // Слайдеры
    val slidersBox = new HBox(20)
    root.getChildren.add(slidersBox)
    slidersBox.setAlignment(Pos.CENTER)

    val sliderBoxX = createSliderBox("X:", wireframeModel.rx)
    slidersBox.getChildren.add(sliderBoxX)
    val sliderBoxY = createSliderBox("Y:", wireframeModel.ry)
    slidersBox.getChildren.add(sliderBoxY)
    val sliderBoxZ = createSliderBox("Z:", wireframeModel.rz)
    slidersBox.getChildren.add(sliderBoxZ)

    // Кнопки
    val buttosnBox = new HBox(20)
    root.getChildren.add(buttosnBox)
    buttosnBox.setAlignment(Pos.CENTER_RIGHT)
    buttosnBox.setPadding(new Insets(20, 20, 20, 20))

    val exitButton = new Button("Exit")
    buttosnBox.getChildren.add(exitButton)
    exitButton.setPrefSize(150, 40)
    exitButton.setOnAction(new EventHandler[ActionEvent] {
      override def handle(event: ActionEvent) = System.exit(0)
    })

  }

  /** Создание панели с заголовком и ползунком */
  private def createSliderBox(title: String, rotate: Rotate) = {
    // Slider
    val minValue = 0
    val maxValue = 360
    val startValue = 0
    val slider = new Slider(minValue, maxValue, startValue)
    slider.valueProperty.addListener(new ChangeListener[Number]() {
      override def changed(observableValue: ObservableValue[_ <: Number], oldValue: Number, newValue: Number) {
        rotate.angleProperty.setValue(newValue)
      }
    })

    // Label
    val label = new Label(title)

    // Box
    val box = new HBox(10)
    box.getChildren.addAll(label, slider)
    box
  }

  /** Панель с каркасной моделью */
  private def getWireframeModelPanel(width: Int, height: Int) = {
    wireframeModel.rx.setAngle(0)
    wireframeModel.ry.setAngle(0)

    val stackPane = new StackPane()
    stackPane.setPrefSize(width, height)
    stackPane.getChildren.add(wireframeModel)
    
    stackPane
  }

}
