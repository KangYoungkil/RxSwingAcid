package model

import rx.Observable
import utils.SysOutUtils
import java.io.File

import java.util.concurrent.TimeUnit

sealed class Model {
    object WordModel : Model() {
        val file = File(javaClass.getResource("/dictionary.txt").toURI())
        private val words: List<String>

        init {
            words = file.readLines(Charsets.UTF_8)
        }
        private const val INTERVAL_TIME: Long = 1 // seconds
        fun words(): Observable<String> {
            val texts = Observable.just(words).flatMapIterable { it -> it }.repeat()
            val trigger = Observable.interval(INTERVAL_TIME, TimeUnit.SECONDS)
            return Observable.zip(texts, trigger) { text, _ -> text }.doOnNext { text -> SysOutUtils.sysout("Sending: $text") }
        }
    }

    object InputModel : Model() {

    }


}