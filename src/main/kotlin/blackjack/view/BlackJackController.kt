package blackjack.view

import blackjack.domain.game.BlackJackGame
import blackjack.domain.game.BlackJackGameFactory
import blackjack.domain.game.BlackJackGameTurn
import blackjack.domain.game.PlayerAnswer

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
                is BlackJackGameTurn.PlayerAnswer -> processPlayerAnswerTurn(blackJackGame, turn)
                is BlackJackGameTurn.Dealer -> processDealerTurn(blackJackGame)
                is BlackJackGameTurn.Finished -> processFinishTurn(blackJackGame)
            }
        } while (turn.hasNextTurn())
    }

    private fun processCardDistributedTurn(
        blackJackGame: BlackJackGame,
    ) {
        val cardDistributionResult = blackJackGame.distributeCardsToPlayers()
        resultView.display(cardDistributionResult)
    }

    private fun processPlayerAnswerTurn(
        blackJackGame: BlackJackGame,
        turn: BlackJackGameTurn.PlayerAnswer,
    ) {
        when (inputView.readHitAnswer(turn.playerName)) {
            PlayerAnswer.HIT -> {
                val hitResult = blackJackGame.hitFocusedPlayer()
                resultView.display(hitResult)
            }
            PlayerAnswer.STAY -> {
                blackJackGame.stayFocusedPlayer()
            }
        }
    }

    private fun processDealerTurn(blackJackGame: BlackJackGame) {
        val executeResult = blackJackGame.executeDealerTurn()
        resultView.display(executeResult)
    }

    private fun processFinishTurn(
        blackJackGame: BlackJackGame,
    ) {
        val gameResult = blackJackGame.makeGameResult()
        resultView.display(gameResult)
    }
}
