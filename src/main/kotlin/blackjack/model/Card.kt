package blackjack.model

class Card private constructor(private val cardNumber: CardNumber, private val suit: Suit) {
    companion object {
        private val CARDS: Map<Suit, Map<CardNumber, Card>> = Suit.values().associate { s ->
            Pair(s, CardNumber.values().associate { cn -> Pair(cn, Card(cn, s)) })
        }

        fun get(cardNumber: CardNumber, suit: Suit): Card {
            return CARDS[suit]!![cardNumber]!!
        }
    }
}
