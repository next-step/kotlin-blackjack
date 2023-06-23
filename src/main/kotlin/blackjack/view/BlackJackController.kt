package blackjack.view

import blackjack.domain.game.BlackJackGame
import blackjack.domain.game.BlackJackGameFactory
import blackjack.domain.game.BlackJackGameTurn
import blackjack.domain.game.HitAnswer

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

        when (val turn = blackJackGame.nextTurn()) {
            is BlackJackGameTurn.HitAnswerWait -> processHitAnswerWaitTurn(blackJackGame, turn)
        }
    }

    private fun processHitAnswerWaitTurn(
        blackJackGame: BlackJackGame,
        turn: BlackJackGameTurn.HitAnswerWait,
    ) {
        when (inputView.readHitAnswer(turn.playerName)) {
            HitAnswer.HIT -> {
                val hitResult = blackJackGame.hitFocusedPlayer()
                resultView.display(hitResult)
            }
            HitAnswer.STAY -> { }
        }
    }
}
