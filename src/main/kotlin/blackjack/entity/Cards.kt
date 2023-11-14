package blackjack.entity

data class Cards(
    val cards: List<Card>
): List<Card> by cards

fun List<Card>.toCards() = Cards(this)
