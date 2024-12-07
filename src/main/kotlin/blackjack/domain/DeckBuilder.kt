package blackjack.domain

fun createDeck(function: DeckBuilder.() -> Unit): Deck {
    return DeckBuilder().apply { function() }.build()
}

class DeckBuilder {
    private val cards: MutableList<Card> = mutableListOf()

    infix fun CardRank.to(suit: Suit) {
        cards.add(Card(this, suit))
    }

    fun build(): Deck {
        if (cards.isEmpty()) {
            throw IllegalArgumentException("카드가 없습니다.")
        }

        return Deck(cards)
    }

    companion object {
        val cachedDeck: Deck = generateFullDeck()

        private fun generateFullDeck(): Deck {
            val suits = Suit.entries.toTypedArray()
            return CardRank.entries.flatMap { rank ->
                suits.map { suit ->
                    Card.of(rank.name, suit)
                }
            }.let {
                Deck(it.shuffled())
            }
        }
    }
}
