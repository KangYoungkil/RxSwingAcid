package model

import rx.Observable
import rx.Scheduler
import rx.schedulers.Schedulers
import utils.SysOutUtils
import java.io.File

import java.util.concurrent.TimeUnit

class  Model {
    val file = File(javaClass.getResource("/dictionary.txt").toURI())
    private val words: List<String>
    private val INTERVAL_TIME: Long = 5 // seconds

    init {
        words = file.readLines(Charsets.UTF_8)
    }
    fun words(): Observable<String> {
        val texts = Observable.just(words.shuffled()).flatMapIterable { it -> it }.filter{it->it.length>2}.repeat()
        val trigger = Observable.interval(INTERVAL_TIME, TimeUnit.SECONDS)
        return Observable.zip(texts, trigger) { text, _ -> text  }.doOnNext { text -> SysOutUtils.sysout("Sending: $text") }.observeOn(Schedulers.io())
    }
}
