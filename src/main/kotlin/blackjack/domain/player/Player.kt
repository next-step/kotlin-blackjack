package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.deck.Deck
import blackjack.domain.game.START_DRAW_COUNT

class Player(
    val name: String,
    private val hands: Hands = Hands()
) {
    fun drawStartHand(deck: Deck) {
        repeat(START_DRAW_COUNT) {
            hands.addCard(deck.popCard())
        }
    }

    fun getHands(): Set<Card> {
        return hands.getCards()
    }

    fun getHandsValue(): Int {
        return hands.getCardsValue()
    }

    fun hit(card: Card) {
        hands.addCard(card)
    }

    private fun isBust(): Boolean {
        return hands.isBust()
    }

    fun race(card: Card, continueGame: (Player) -> Boolean, afterDrawCard: (Player) -> Unit) {
        while (!isBust() && continueGame(this)) {
            hit(card)
            afterDrawCard(this)
        }
    }

    companion object {
        fun of(name: String): Player {
            return Player(name)
        }
    }
}
