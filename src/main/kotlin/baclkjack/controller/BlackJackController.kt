package baclkjack.controller

import baclkjack.domain.BlackJackGame
import baclkjack.domain.play.CardDrawListener
import baclkjack.view.InputView
import baclkjack.view.ResultView
import baclkjack.view.toCards
import baclkjack.view.toResult

class BlackJackController(
    private val inputView: InputView = InputView,
    private val resultView: ResultView = ResultView
) {

    fun play() {
        val players = inputView.inputPlayer()
        val backJackGame = BlackJackGame(players)

        resultView.showHit(backJackGame.dealer.name, players.joinToString())
        backJackGame.start()
        backJackGame.dealer.also {
            resultView.showPlayerCard(it.name, it.cards.toCards())
        }
        backJackGame.players.forEach {
            resultView.showPlayerCard(it.name, it.cards.toCards())
        }

        backJackGame.play(object : CardDrawListener {
            override fun isDraw(name: String): Boolean {
                return inputView.inputCardDraw(name) == IS_DRAW
            }
        }) {
            resultView.showPlayerCard(it.name, it.cards.toCards())
        }

        backJackGame.dealerPlay {
            resultView.showDealerCard(it.name)
        }

        backJackGame.dealer.also {
            resultView.showPlayerResult(it.name, it.cards.toCards(), it.score())
        }
        backJackGame.players.forEach {
            resultView.showPlayerResult(it.name, it.cards.toCards(), it.score())
        }

        resultView.showFinal()
        backJackGame.dealer.also {
            resultView.showWinner(it.name, it.result(backJackGame.players).toResult())
        }

        backJackGame.players.forEach {
            resultView.showWinner(it.name, it.result(backJackGame.dealer).toResult())
        }
    }

    companion object {
        const val IS_DRAW = "y"
    }
}
