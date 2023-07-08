package baclkjack.controller

import baclkjack.domain.BlackJackGame
import baclkjack.domain.play.CardDrawListener
import baclkjack.domain.play.Money
import baclkjack.domain.play.Player
import baclkjack.domain.play.toPlayers
import baclkjack.view.InputView
import baclkjack.view.ResultView
import baclkjack.view.toCards

class BlackJackController(
    private val inputView: InputView = InputView,
    private val resultView: ResultView = ResultView
) {

    fun play() {
        val playerNames = inputView.inputPlayer()

        val players = playerNames.map {
            val value = inputView.inputBetting(it)
            Player(name = it, money = Money(value))
        }.toPlayers()

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
            resultView.showWinner(it.name, it.result(backJackGame.players).toString())
        }

        backJackGame.players.forEach {
            resultView.showWinner(it.name, it.result(backJackGame.dealer).toString())
        }
    }

    companion object {
        const val IS_DRAW = "y"
    }
}
