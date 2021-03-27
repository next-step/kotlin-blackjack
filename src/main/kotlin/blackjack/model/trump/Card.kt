package blackjack.model.trump

import blackjack.model.score.Scores

class Card internal constructor(val cardNumber: CardNumber, val suit: Suit) {
    fun isAce(): Boolean {
        return cardNumber == CardNumber.ACE
    }

    fun getScores(): Scores {
        return cardNumber.scores
    }

    companion object {
        private val DECK = Deck()

        fun get(cardNumber: CardNumber, suit: Suit): Card {
            return DECK[suit]!![cardNumber]!!
        }

        fun get(): Card {
            return DECK[Suit.values().random()]!![CardNumber.values().random()]!!
        }
    }
}
