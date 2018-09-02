package view_model


import model.Model
import mvvm.IViewModel
import net.jcip.annotations.ThreadSafe
import rx.subjects.BehaviorSubject
import rx.subjects.PublishSubject
import java.awt.event.KeyEvent


@ThreadSafe
class ViewModel : IViewModel<Model> {

    val wordEvent = BehaviorSubject.create<String>()
    val enterEvents = PublishSubject.create<KeyEvent>()
    val inputTextEvents = BehaviorSubject.create<String>()
    val inputClearEvents = BehaviorSubject.create<String>()
    val correctEvents = BehaviorSubject.create<String>()
    val correntCountEvents = BehaviorSubject.create<String>()


    init {
        wireInternally()
        correntCountEvents.onNext("0")
    }

    private fun wireInternally() {
        enterEvents.filter { it.keyCode == KeyEvent.VK_ENTER && it.id == KeyEvent.KEY_PRESSED }
                .subscribe {

                    if(inputTextEvents.value == wordEvent.value){
                        correctEvents.onNext(wordEvent.value)
                        correntCountEvents.onNext(correctEvents.values.size.toString()+"\t")
                    }

                    println(inputTextEvents.value)
                    inputClearEvents.onNext("")
                }
    }

    override fun connectTo(model: Model) {
        model.words().subscribe(this.wordEvent)


    }

}