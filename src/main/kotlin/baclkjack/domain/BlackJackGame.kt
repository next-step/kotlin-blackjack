package baclkjack.domain

import baclkjack.domain.card.Deck
import baclkjack.domain.play.Dealer
import baclkjack.domain.play.Player

class BlackJackGame(players: List<String>, private val deck: Deck = Deck.createDeck()) {

    val players = players.map { Player(it) }
    val dealer = Dealer()

    fun start() {
        dealer.start(deck)
        players.forEach {
            it.start(deck)
        }
    }

    fun play(draw: (String) -> Boolean, out: (Player) -> Unit) {
        players.forEach {
            it.draw = draw
            playerDraw(it, out)
        }
    }

    fun dealerPlay(out: (Player) -> Unit) {
        playerDraw(dealer, out)
    }

    private fun playerDraw(player: Player, out: (Player) -> Unit) {
        while (player.isDraw()) {
            player.hit(deck)
            out(player)
            if (player.finish()) {
                break
            }
        }
    }
}
