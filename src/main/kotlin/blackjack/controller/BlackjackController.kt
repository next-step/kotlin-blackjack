package blackjack.controller

import blackjack.model.Cards
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
        var players = names.map { Player(it, Cards.empty()) }.let(Players::of)
        deck = Deck.shuffled()
        players = firstDraw(players)
        outputView.printFirstDraw(players)
        players = drawWhileNeeded(players) { player -> outputView.printPlayerCards(player) }
        outputView.printResult(players)
    }

    private fun firstDraw(players: Players): Players {
        var result = players
        repeat(FIRST_DRAW_COUNT) {
            result = result.flatMap { player -> draw(player) }
        }
        return result
    }

    private fun draw(player: Player): Player {
        val card = deck.peek() ?: return player
        deck = deck.draw()
        return player.receive(card)
    }

    private fun drawWhileNeeded(
        players: Players,
        onDraw: (Player) -> Unit
    ): Players = players.flatMap { player -> drawWhileNeeded(player, onDraw) }

    private fun drawWhileNeeded(player: Player, onDraw: (Player) -> Unit): Player {
        if (deck.isEmpty()) return player
        var result = player
        while (!deck.isEmpty() && askDraw(player)) {
            result = draw(result)
            onDraw(result)
        }
        return result
    }

    private fun askDraw(player: Player): Boolean = inputView.askDraw(player) == DrawAction.YES

    companion object {
        private const val FIRST_DRAW_COUNT = 2
    }
}
