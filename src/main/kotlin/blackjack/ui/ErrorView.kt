package blackjack.ui

import blackjack.strategy.ui.output.OutputStrategy

class ErrorView(private val outputStrategy: OutputStrategy) {
    fun showErrorMessage(errorMessage: String): Unit = outputStrategy.execute(errorMessage)
}
