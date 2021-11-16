package blackjack.strategy.ui.input

object ConsoleInputStrategy : InputStrategy {
    override fun execute(): String = readLine() ?: throw IllegalArgumentException()
}
