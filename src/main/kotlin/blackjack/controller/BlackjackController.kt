package blackjack.controller

import blackjack.model.Cards
import blackjack.model.Deck
import blackjack.model.Player
import blackjack.view.DrawAction
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackController {

    private val inputView = InputView()
    private val outputView = OutputView()

    private var deck: Deck = Deck.empty()

    fun play() {
        val names = inputView.getNames() ?: return
        var players = names.map { Player(it, Cards.empty()) }
        deck = Deck.shuffled()
        players = drawAll(players, FIRST_DRAW_COUNT)
        outputView.printFirstDraw(players, FIRST_DRAW_COUNT)
        players = drawWhileNeeded(players) { player -> outputView.printPlayerCards(player) }
        outputView.printResult(players)
    }

    private fun drawAll(players: List<Player>, repeat: Int): List<Player> {
        var result = players
        repeat(repeat) { result = drawAll(result) }
        return result
    }

    private fun drawAll(players: List<Player>): List<Player> = players.map { player ->
        val card = deck.peek() ?: return@map player
        deck = deck.draw()
        player.receive(card)
    }

    private fun drawWhileNeeded(
        players: List<Player>,
        onDraw: (Player) -> Unit
    ): List<Player> = players.map { player -> drawWhileNeeded(player, onDraw) }

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

    private fun draw(player: Player): Player {
        val card = deck.peek()!!
        deck = deck.draw()
        return player.receive(card)
    }

    companion object {
        private const val FIRST_DRAW_COUNT = 2
    }
}
