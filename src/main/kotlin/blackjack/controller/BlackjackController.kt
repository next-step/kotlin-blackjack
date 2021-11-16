package blackjack.controller

import blackjack.model.Card
import blackjack.model.Deck
import blackjack.model.Player
import blackjack.model.Players
import blackjack.view.DrawAction
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackController {

    private val inputView = InputView()
    private val outputView = OutputView()

    private var deck: Deck = Deck.empty()

    fun play() {
        val names = inputView.getNames() ?: return
        var players = Players.from(names)
        deck = Deck.shuffled()
        players = firstDraw(players)
        outputView.printFirstDraw(players, FIRST_DRAW_COUNT)
        players = drawWhileNeeded(players) { player -> outputView.printPlayerCards(player) }
        outputView.printResult(players)
    }

    private fun firstDraw(players: Players): Players = players.receiveWhile(FIRST_DRAW_COUNT) {
        if (deck.isNotEmpty()) {
            peekAndDraw()
        } else {
            null
        }
    }

    private fun drawWhileNeeded(
        players: Players,
        onDraw: (Player) -> Unit,
    ): Players = players.receiveWhile { player ->
        if (deck.isNotEmpty() && askDraw(player)) {
            peekAndDraw()?.also { onDraw(player) }
        } else {
            null
        }
    }

    private fun askDraw(player: Player): Boolean = inputView.askDraw(player) == DrawAction.YES

    private fun peekAndDraw(): Card? {
        val card = deck.peek() ?: return null
        deck = deck.draw()
        return card
    }

    companion object {
        private const val FIRST_DRAW_COUNT = 2
    }
}
