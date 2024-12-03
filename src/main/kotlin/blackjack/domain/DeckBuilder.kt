package blackjack.domain

fun createDeck(function: DeckBuilder.() -> Unit): Deck {
    return DeckBuilder().apply { function() }.build()
}

class DeckBuilder {
    private val cards: MutableList<Card> = mutableListOf()

    infix fun String.to(suit: Suit) {
        cards.add(Card.of(this, suit))
    }

    fun build(): Deck {
        if (cards.isEmpty()) {
            throw IllegalArgumentException("카드가 없습니다.")
        }

        val d = Deck(cards)
        return d
    }

    companion object {
        val cachedDeck: Deck = generateFullDeck()

        private fun generateFullDeck(): Deck {
            val ranks = listOf("A", "2", "3", "4", "5", "6", "7", "8", "9", "J", "Q", "K", "J")
            val suits = Suit.entries.toTypedArray()
            return ranks.flatMap { rank ->
                suits.map { suit ->
                    Card.of(rank, suit)
                }
            }.let {
                Deck(it.shuffled())
            }
        }
    }
}
