package baclkjack.domain

import baclkjack.domain.card.Deck
import baclkjack.domain.play.Player

class BlackJackGame(players: List<String>, private val deck: Deck = Deck.createDeck()) {

    val players = players.map { Player(it) }

    fun start() {
        players.forEach {
            it.start(deck)
        }
    }

    fun play(isDraw: (String) -> Boolean, out: (Player) -> Unit) {
        players.forEach {
            playerDraw(it, isDraw, out)
        }
    }

    private fun playerDraw(player: Player, isDraw: (String) -> Boolean, out: (Player) -> Unit) {
        while (isDraw(player.name)) {
            player.hit(deck)
            out(player)
            if (player.burst() || player.blackJack()) {
                break
            }
        }
    }
}
