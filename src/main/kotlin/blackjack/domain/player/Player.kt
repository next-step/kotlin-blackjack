package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.deck.Deck

class Player(
    val name: String,
    private val hands: Hands = Hands()
) {
    fun drawStartHand(deck: Deck) {
        repeat(2) {
            hands.addCard(deck.getOneCard())
        }
    }

    fun getHands(): Set<Card> {
        return hands.getCards()
    }

    fun getHandsValue(): Int {
        return hands.getCardsValue()
    }

    fun hit(deck: Deck) {
        hands.addCard(deck.getOneCard())
    }

    fun canPlayable(): Boolean {
        return hands.isBust()
    }

    companion object {
        fun of(name: String): Player {
            return Player(name)
        }
    }
}
