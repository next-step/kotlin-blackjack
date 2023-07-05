package baclkjack.domain

import baclkjack.domain.card.Deck
import baclkjack.domain.play.Dealer
import baclkjack.domain.play.Player
import baclkjack.domain.play.User

class BlackJackGame(playersName: List<String>, private val deck: Deck = Deck.createDeck()) {

    val players = playersName.map { Player(it) }
    val dealer = Dealer()

    fun start() {
        dealer.start(deck)
        players.forEach {
            it.start(deck)
        }
    }

    fun play(draw: (String) -> Boolean, out: (User) -> Unit) {
        players.forEach {
            it.draw = draw
            playerDraw(it, out)
        }
    }

    fun dealerPlay(out: (User) -> Unit) {
        playerDraw(dealer, out)
    }

    private fun playerDraw(player: User, out: (User) -> Unit) {
        while (player.isDraw()) {
            player.hit(deck)
            out(player)
            if (player.finish()) {
                break
            }
        }
    }
}
