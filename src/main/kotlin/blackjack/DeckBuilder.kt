package blackjack

fun createDeck(function: DeckBuilder.() -> Unit): Deck {
    return DeckBuilder().apply { function() }.build()
}

class DeckBuilder {
    private var cards: List<Card> = emptyList()

    fun cards(block: CardBuilder.() -> Unit) {
        cards = CardBuilder().apply(block).build()
    }

    fun build(): Deck {
        if (cards.isEmpty()) {
            throw IllegalArgumentException("카드가 없습니다.")
        }

        return Deck(cards)
    }

    companion object {
        val cachedDeck: Deck = generateFullDeck()

        fun createDeck(): Deck {
            return cachedDeck.copy()
        }

        private fun generateFullDeck(): Deck {
            val ranks = listOf("A", "2", "3", "4", "5", "6", "7", "8", "9", "J", "Q", "K", "J")
            val suits = Suit.entries.toTypedArray()
            return ranks.flatMap { rank ->
                suits.map { suit ->
                    Card(rank, suit)
                }
            }.let {
                Deck(it.shuffled())
            }
        }
    }
}
