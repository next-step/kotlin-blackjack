package blackjack.strategy.ui.output

object ConsoleOutputStrategy : OutputStrategy {
    override fun execute(message: String): Unit = println(message)
}
