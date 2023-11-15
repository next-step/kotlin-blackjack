package blackjack.entity

data class Cards(
    val cards: List<Card>
) : List<Card> by cards {
    override fun toString(): String {
        return cards.joinToString { card -> card.toString() }
    }
}

fun List<Card>.toCards() = Cards(this)
