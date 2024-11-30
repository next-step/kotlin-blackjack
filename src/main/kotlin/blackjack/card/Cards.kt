package blackjack.card

class Cards(
    val cards: List<Card>,
) {
    fun sum(): Int = cards.sumOf { it.rank.value }

    fun add(newCard: Card): Cards = Cards(cards = cards + newCard)
}
