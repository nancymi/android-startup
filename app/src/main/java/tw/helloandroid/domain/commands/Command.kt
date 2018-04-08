package tw.helloandroid.domain.commands

interface Command<out T> {
    fun execute(): T
}
