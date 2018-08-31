package view_model


import model.Model
import mvvm.IViewModel
import net.jcip.annotations.ThreadSafe
import rx.subjects.BehaviorSubject
import rx.subjects.PublishSubject
import java.awt.event.KeyEvent




@ThreadSafe
class ViewModel : IViewModel<Model> {

    val wordEvent = BehaviorSubject.create<String>()!!
    val enterEvents = PublishSubject.create<KeyEvent>()
    val inputWord = BehaviorSubject.create<String>()


    init {
        wireInternally()
    }

    private fun wireInternally() {
        enterEvents.filter { it.keyCode == KeyEvent.VK_ENTER }
                .subscribe {
                    println(inputWord.value)
                }
    }

    override fun connectTo(model: Model) {
        when (model) {
            is Model.WordModel -> {
                model.words().subscribe(this.wordEvent)
            }
            is Model.InputModel -> {

            }
        }
    }

}