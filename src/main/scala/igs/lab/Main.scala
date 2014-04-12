package igs.lab

import javafx.application.Platform
import javafx.scene.{PerspectiveCamera, Scene}
import javafx.scene.layout.{HBox, StackPane, VBox}
import javafx.scene.paint.Color
import javafx.embed.swing.JFXPanel
import scala.swing.{FlowPanel, MainFrame, SimpleSwingApplication}
import javafx.scene.control.{Label, Slider, Button}
import javafx.beans.value.{ObservableValue, ChangeListener}
import javafx.event.{ActionEvent, EventHandler}
import javafx.geometry.{Insets, Pos}
import java.awt.Dimension
import igs.lab.shape.{RotateShape, Cube, Ball}
import igs.lab.Util.AxisTitle

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

  /** Модель поверхности */
  private val SizeModel = 100
  private val ColorNum = 240
  private val ColorModel = if (false) null else Color.rgb(ColorNum, ColorNum, ColorNum)
  private val ShadeModel = 0.5
  private val CubeModel = new Cube(SizeModel, ColorModel, ShadeModel)
  private val BallModel = new Ball(50)
  private var objectModel: RotateShape = CubeModel
  private val jfx = new JFXPanel
  /** Непрозрачность **/
  private val OpaqueModel = true

  /** Создание главной формы */
  def top = new MainFrame {
    contents = new FlowPanel {
      peer.add(jfx)
      jfx.setPreferredSize(new Dimension(FormWidth, FormHeight))
    }
    centerOnScreen()
  }

  /** Отложенный запуск создания Java FX компонентов */
  Platform.runLater(new Runnable {
    override def run() = createJavaFxComponents()
  })

  /** Создание Java FX компонентов */
  private def createJavaFxComponents() {
    val root = new VBox(20)
    val scene = new Scene(root, ModelWidth, ModelHeight, OpaqueModel)
    scene.setCamera(new PerspectiveCamera)
    jfx.setScene(scene)

    // Модель объекта
    var objectModelPanel = getObjectModelPanel(ModelWidth, ModelHeight)
    root.getChildren.add(objectModelPanel)

    // Слайдеры
    val slidersBox = new HBox(20)
    root.getChildren.add(slidersBox)
    slidersBox.setAlignment(Pos.CENTER)

    val sliderBoxX = createSliderBox(AxisTitle.X)
    slidersBox.getChildren.add(sliderBoxX)
    val sliderBoxY = createSliderBox(AxisTitle.Y)
    slidersBox.getChildren.add(sliderBoxY)
    val sliderBoxZ = createSliderBox(AxisTitle.Z)
    slidersBox.getChildren.add(sliderBoxZ)

    // Кнопки
    val buttosnBox = new HBox(20)
    root.getChildren.add(buttosnBox)
    buttosnBox.setAlignment(Pos.CENTER_RIGHT)
    buttosnBox.setPadding(new Insets(20, 20, 20, 20))

    //  Смена фигуры
    val changeObjectButton = new Button("Change shape")
    buttosnBox.getChildren.add(changeObjectButton)
    changeObjectButton.setOnAction(new EventHandler[ActionEvent] {
      override def handle(event: ActionEvent) {
        root.getChildren.remove(objectModelPanel)
        objectModel match {
          case cube: Cube => objectModel = BallModel
          case ball: Ball => objectModel = CubeModel
        }
        objectModelPanel = getObjectModelPanel(ModelWidth, ModelHeight)
        root.getChildren.add(0, objectModelPanel)
      }
    })

    //  Построение каркасной модели
    val wireframeModelButton = new Button("Wireframe model")
    buttosnBox.getChildren.add(wireframeModelButton)
    wireframeModelButton.setOnAction(new EventHandler[ActionEvent] {
      override def handle(event: ActionEvent) = objectModel.wireframeModel()
    })

    //  Смена цвета объекта
    val changeColorsButton = new Button("Change colors")
    buttosnBox.getChildren.add(changeColorsButton)
    changeColorsButton.setOnAction(new EventHandler[ActionEvent] {
      override def handle(event: ActionEvent) = objectModel.changeColors()
    })

    //  Выход из программы
    val exitButton = new Button("Exit")
    buttosnBox.getChildren.add(exitButton)
    exitButton.setPrefSize(150, 40)
    exitButton.setOnAction(new EventHandler[ActionEvent] {
      override def handle(event: ActionEvent) = System.exit(0)
    })

  }

  /** Создание панели с заголовком и ползунком */
  private def createSliderBox(title: String) = {
    // Slider
    val minValue = 0
    val maxValue = 360
    val startValue = 0
    val slider = new Slider(minValue, maxValue, startValue)

    // Поворот объекта по оси при изменении положения ползунка
    slider.valueProperty.addListener(new ChangeListener[Number]() {
      override def changed(observableValue: ObservableValue[_ <: Number], oldValue: Number, newValue: Number) {
        val rotate = title match {
          case AxisTitle.X => objectModel.rx
          case AxisTitle.Y => objectModel.rx
          case AxisTitle.Z => objectModel.rx
        }

        rotate.setAngle(newValue.doubleValue)
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
  private def getObjectModelPanel(width: Int, height: Int) = {
    // Установка повортов по всем осям в 0
    objectModel.rx.setAngle(0)
    objectModel.ry.setAngle(0)
    objectModel.rz.setAngle(0)

    val stackPane = new StackPane()
    stackPane.setPrefSize(width, height)
    stackPane.getChildren.add(objectModel)
    
    stackPane
  }

}
