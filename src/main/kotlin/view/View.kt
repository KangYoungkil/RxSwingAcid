package view


import widgets.StrictThreadingJLabel
import java.awt.*;
import java.lang.management.ManagementFactory;
import java.awt.BorderLayout
import javax.swing.SwingConstants
import widgets.StrictThreadingJFrame
import mvvm.IView
import mvvm.RxSwingView2ViewModelBinder.bindSwingView
import mvvm.RxSwingView2ViewModelBinder.bindSwingViewKeyEvent
import mvvm.RxViewModel2SwingViewBinder.*
import view_model.ViewModel
import widgets.StrictThreadingJTextField


internal class View : StrictThreadingJFrame(), IView<ViewModel> {

    private val label: StrictThreadingJLabel
    val inputTextFeild: StrictThreadingJTextField
    private val pointLabel: StrictThreadingJLabel

    val WIDTH = 640
    val HEIGHT = 360

    override fun bind(viewModel: ViewModel) {
        bindViewModelString(viewModel.wordEvent).toSwingViewLabel(label)
        bindSwingViewKeyEvent(inputTextFeild).toViewModel(viewModel.enterEvents)
        bindSwingView(inputTextFeild).toViewModel(viewModel.inputTextEvents)
        bindViewModelString(viewModel.inputClearEvents).toSwingViewText(inputTextFeild)
        bindViewModelString(viewModel.correntCountEvents).toSwingViewLabel(pointLabel)
    }

    init {
        title = javaClass.simpleName + " " + ManagementFactory.getRuntimeMXBean().name

        println("height:$height, width:$width")

        setBounds(100, 100, WIDTH, HEIGHT)
        defaultCloseOperation = StrictThreadingJFrame.EXIT_ON_CLOSE
        contentPane.layout = BorderLayout(0, 0)

        label = StrictThreadingJLabel()
        label.foreground = Color.BLUE
        label.font = Font("Serif", Font.BOLD, 13)

        val gridLayout = GridLayout(10,5,0,0)
        val gridContainer = Container()
        gridContainer.layout = gridLayout


        gridContainer.add(label,0)
        for (x in 1..38)
        gridContainer.add(StrictThreadingJLabel(x.toString()))


        contentPane.add(gridContainer,BorderLayout.CENTER)


        pointLabel = StrictThreadingJLabel()
        pointLabel.foreground = Color.RED
        pointLabel.horizontalAlignment = SwingConstants.RIGHT
        pointLabel.font = Font("Serif", Font.BOLD, 15)

        contentPane.add(pointLabel, BorderLayout.NORTH)
        pointLabel.text = "10"

        inputTextFeild = StrictThreadingJTextField()
        contentPane.add(inputTextFeild, BorderLayout.SOUTH)

    }
}
