package blackjack.model

class Card private constructor(val cardNumber: CardNumber, val suit: Suit) {
    fun isAce(): Boolean {
        return cardNumber == CardNumber.ACE
    }

    fun getScores(): Scores {
        return cardNumber.scores
    }

    override fun toString(): String {
        return "$cardNumber$suit"
    }

    companion object {
        private val CARDS: Map<Suit, Map<CardNumber, Card>> = Suit.values().associate { s ->
            Pair(s, CardNumber.values().associate { cn -> Pair(cn, Card(cn, s)) })
        }

        fun get(cardNumber: CardNumber, suit: Suit): Card {
            return CARDS[suit]!![cardNumber]!!
        }

        fun get(): Card {
            return CARDS[Suit.values().random()]!![CardNumber.values().random()]!!
        }
    }
}
