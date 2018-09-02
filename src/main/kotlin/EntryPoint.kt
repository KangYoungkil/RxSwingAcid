import model.Model
import utils.SwingUtilities2
import utils.SysOutUtils
import utils.UncaughtExceptionHandlerInitializer
import view.View
import view_model.ViewModel
import java.lang.management.ManagementFactory
import javax.swing.UIManager

fun main(args: Array<String>) {
    SysOutUtils.sysout(ManagementFactory.getRuntimeMXBean().name)
    UncaughtExceptionHandlerInitializer.initUncaughtExceptionHandler()

    val wordModel = Model()
    val viewModel = ViewModel()
    viewModel.connectTo(wordModel)

    SwingUtilities2.invokeLater {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName())
        val view = View()
        view.bind(viewModel)
        view.isVisible = true
    }
}