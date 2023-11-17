package blackjack.entity

data class Cards(
    val cards: List<Card>
) : List<Card> by cards {
    val cardsContainACard: Boolean
        get() {
            return cards.any { card -> card.number == CardNumber.A }
        }

    val sumOfCards: Int
        get() = cards.sumOf { it.number.number }

    fun addNewCard(card: Cards): Cards = Cards(cards + card)

    override fun toString(): String {
        return cards.joinToString { card -> card.toString() }
    }
}

fun List<Card>.toCards() = Cards(this)
