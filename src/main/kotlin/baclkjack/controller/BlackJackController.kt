package baclkjack.controller

import baclkjack.domain.BlackJackGame
import baclkjack.view.InputView
import baclkjack.view.ResultView
import baclkjack.view.toCards

class BlackJackController(
    private val inputView: InputView = InputView,
    private val resultView: ResultView = ResultView
) {

    fun play() {
        val players = inputView.inputPlayer()
        resultView.showHit(players.joinToString())

        val backJackGame = BlackJackGame(players)
        backJackGame.start()
        backJackGame.players.forEach {
            resultView.showPlayerCard(it.name, it.cards().toCards())
        }

        backJackGame.play(
            draw = {
                inputView.inputCardDraw(it) == IS_DRAW
            },
            out = {
                val cards = it.cards().toCards()
                resultView.showPlayerCard(it.name, cards)
            }
        )
        backJackGame.players.forEach {
            resultView.showPlayerResult(it.name, it.cards().toCards(), it.result())
        }
    }

    companion object {
        const val IS_DRAW = "y"
    }
}
