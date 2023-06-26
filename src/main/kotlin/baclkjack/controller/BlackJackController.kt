package baclkjack.controller

import baclkjack.domain.BlackJackGame
import baclkjack.view.InputView
import baclkjack.view.ResultView

class BlackJackController(
    private val inputView: InputView = InputView,
    private val resultView: ResultView = ResultView
) {

    fun play() {
        val players = inputView.inputPlayer()
        resultView.showHit(players.joinToString { name -> name })

        val backJackGame = BlackJackGame(players)
        backJackGame.startDraw()
        backJackGame.forPlayer {
            resultView.showPlayerCard(it.name, it.cardList())
        }

        backJackGame.playerStay(
            isDraw = {
                inputView.inputCardDraw(it) == IS_DRAW
            },
            out = {
                val cards = it.cardList()
                resultView.showPlayerCard(it.name, cards)
            }
        )
        backJackGame.forPlayer {
            resultView.showPlayerResult(it.name, it.cardList(), it.result())
        }

    }

    companion object {
        const val IS_DRAW = "y"
    }
}
