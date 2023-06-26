package baclkjack.domain

import baclkjack.domain.card.Deck
import baclkjack.domain.play.Player

class BlackJackGame(player: List<String>, private val deck: Deck = Deck.createDeck()) {

    val players = player.map { Player(it) }

    fun startDraw() {
        players.forEach {
            it.start(deck)
        }
    }

    fun playerStay(isDraw: (String) -> Boolean, out: (Player) -> Unit) {
        players.forEach {
            while (isDraw(it.name) || it.burst()) {
                it.hit(deck)
                out(it)
            }
        }
    }

    fun forPlayer(player: (Player) -> Unit) {
        players.forEach {
            player(it)
        }
    }
}
