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

        do {
            val turn = blackJackGame.currentTurn()
            when (turn) {
                is BlackJackGameTurn.CardDistribution -> processCardDistributedTurn(blackJackGame)
                is BlackJackGameTurn.HitOrStay -> processHitOrStayTurn(blackJackGame, turn)
                is BlackJackGameTurn.Finished -> processFinishTurn(blackJackGame)
            }
        } while (turn.isFinished().not())
    }

    private fun processCardDistributedTurn(
        blackJackGame: BlackJackGame,
    ) {
        val cardDistributionResult = blackJackGame.distributeCardsToPlayers()
        resultView.display(cardDistributionResult)
    }

    private fun processHitOrStayTurn(
        blackJackGame: BlackJackGame,
        turn: BlackJackGameTurn.HitOrStay,
    ) {
        when (inputView.readHitAnswer(turn.playerName)) {
            HitAnswer.HIT -> {
                val hitResult = blackJackGame.hitFocusedPlayer()
                resultView.display(hitResult)
            }
            HitAnswer.STAY -> {
                blackJackGame.stayFocusedPlayer()
            }
        }
    }

    private fun processFinishTurn(
        blackJackGame: BlackJackGame,
    ) {
        val gameResult = blackJackGame.makeGameResult()
        resultView.display(gameResult)
    }
}
