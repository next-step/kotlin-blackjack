package blackjack.model

class CardDeck private constructor(
    private val cards: MutableList<Card>
) : List<Card> by cards {
    fun drawCard(): Card {
        cards.shuffle()
        return cards.removeFirst()
    }

    fun drawCards(count: Int): Cards {
        return Cards(List(count) { drawCard() })
    }

    companion object {
        fun of(vararg cards: Card): CardDeck {
            return of(cards.toList())
        }

        fun of(cards: List<Card>): CardDeck {
            return CardDeck(cards.toMutableList())
        }

        fun defaultDeck(): CardDeck {
            return of(
                Suit.values().flatMap { suit -> Denomination.values().map { Card(suit, it) } }
            )
        }
    }
}
