package blackjack.model

class Card private constructor(private val suit: Suit, private val rank: Rank) {
    val isAce: Boolean = rank == Rank.ACE

    fun getValue(): Int = rank.value

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Card) return false

        return suit == other.suit && rank == other.rank
    }

    override fun hashCode(): Int {
        var result = suit.hashCode()
        result = 31 * result + rank.hashCode()
        return result
    }

    override fun toString(): String =
        when (rank) {
            Rank.JACK -> "J${suit.description}"
            Rank.QUEEN -> "Q${suit.description}"
            Rank.KING -> "K${suit.description}"
            Rank.ACE -> "A${suit.description}"
            else -> "${rank.value}${suit.description}"
        }

    companion object {
        private var allCards: MutableSet<Card> = generateAllCards().toMutableSet()

        private fun generateAllCards(): Set<Card> {
            val cards = mutableSetOf<Card>()

            for (suit in Suit.list) {
                for (rank in Rank.list) {
                    cards.add(createCard(suit, rank))
                }
            }

            return cards
        }

        private fun createCard(suit: Suit, rank: Rank): Card {
            return Card(suit, rank)
        }

        fun takeRandomCard(): Card {
            if (allCards.isEmpty()) throw IllegalStateException("남는 카드가 없습니다.")
            val card = allCards.random()
            allCards.remove(card)
            return card
        }

        fun resetAllCards() {
            allCards = generateAllCards().toMutableSet()
        }
    }
}
