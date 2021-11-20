package blackjack.strategy.ui.input

import blackjack.error.ReadLineNullPointerException

object ConsoleInputStrategy : InputStrategy {
    override fun execute(): String = readLine() ?: throw ReadLineNullPointerException()
}
