package blackjack.view

import blackjack.domain.game.BlackJackGameFactory

class BlackJackController(
    private val inputView: BlackJackInputView,
    private val resultView: BlackJackResultView,
    private val blackJackGameFactory: BlackJackGameFactory,
) {

    fun start() {
        val playerNames = inputView.readPlayerNames()
        val blackJackGame = blackJackGameFactory.create(playerNames)

        val cardDistributionResult = blackJackGame.distributeCardsToPlayers()
        resultView.display(cardDistributionResult)
    }
}
