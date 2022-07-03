package blackjack.domain

import blackjack.domain.user.Dealer
import blackjack.domain.user.Player
import blackjack.domain.user.User

class Blackjack(
    val players: List<Player>
) {
    val dealer = Dealer()

    private val deck = Deck.create()

    fun drawFirstCards() {
        check(players.all { it.isEmptyCards() }) { "Player must not have any cards" }
        check(dealer.isEmptyCards()) { "Dealer must not have any cards." }
        players.forEach {
            val cards = deck.draw(INIT_DRAW_COUNT)
            it.addCards(cards)
        }
        dealer.addCards(deck.draw(INIT_DRAW_COUNT))
    }

    fun drawCard(user: User) {
        user.addCard(deck.draw())
    }

    companion object {
        private const val INIT_DRAW_COUNT = 2
    }
}
