package baclkjack.domain

import baclkjack.domain.card.Deck
import baclkjack.domain.play.CardDrawListener
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

    fun play(cardDrawListener: CardDrawListener, out: (User) -> Unit) {
        players.forEach {
            it.cardDrawListener = cardDrawListener
            playGame(it, out)
        }
    }

    fun dealerPlay(out: (User) -> Unit) {
        playGame(dealer, out)
    }

    private fun playGame(user: User, out: (User) -> Unit) {
        while (user.isDraw()) {
            user.hit(deck)
            out(user)
            if (user.burst() || user.blackJack()) {
                break
            }
        }
    }
}
