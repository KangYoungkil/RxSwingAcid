package view


import javafx.scene.input.KeyCode
import widgets.StrictThreadingJLabel
import java.awt.*;
import java.lang.management.ManagementFactory;
import java.awt.BorderLayout
import javax.swing.SwingConstants
import widgets.StrictThreadingJFrame
import mvvm.RxViewModel2SwingViewBinder.bindViewModelString
import mvvm.IView
import mvvm.RxSwingView2ViewModelBinder.bindSwingView
import mvvm.RxSwingView2ViewModelBinder.bindSwingViewKeyEvent
import mvvm.RxViewModel2SwingViewBinder.bindViewModel
import rx.Observable
import rx.Observer
import rx.swing.sources.KeyEventSource
import view_model.ViewModel
import widgets.StrictThreadingJTextField
import java.awt.event.KeyEvent
import java.awt.event.KeyListener


internal class View : StrictThreadingJFrame(), IView<ViewModel> {

    private val label: StrictThreadingJLabel
    val input: StrictThreadingJTextField
    private val pointLabel: StrictThreadingJLabel

    val WIDTH = 1280
    val HEIGHT = 720

    override fun bind(viewModel: ViewModel) {
        bindViewModelString(viewModel.wordEvent).toSwingViewLabel(label)
        bindSwingViewKeyEvent(input).toViewModel(viewModel.enterEvents)
        bindSwingView(input).toViewModel(viewModel.inputWord)
    }

    init {
        title = javaClass.simpleName + " " + ManagementFactory.getRuntimeMXBean().name

        println("height:$height, width:$width")

        setBounds(100, 100, WIDTH, HEIGHT)
        defaultCloseOperation = StrictThreadingJFrame.EXIT_ON_CLOSE
        contentPane.layout = BorderLayout(0, 0)

        label = StrictThreadingJLabel()
        label.foreground = Color.BLUE
        label.font = Font("Serif", Font.BOLD, 20)
        label.horizontalAlignment = SwingConstants.CENTER
        contentPane.add(label, BorderLayout.CENTER)

        pointLabel = StrictThreadingJLabel()
        pointLabel.foreground = Color.RED
        pointLabel.font = Font("Serif", Font.BOLD, 15)

        contentPane.add(pointLabel, BorderLayout.NORTH)
        label.text = "10"

        input = StrictThreadingJTextField()
        contentPane.add(input, BorderLayout.SOUTH)

    }
}
