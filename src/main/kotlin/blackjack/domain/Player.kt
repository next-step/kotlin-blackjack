package blackjack.domain

class Player(val name: String, initCards: List<Card>) {
    val cards: MutableList<Card> = initCards.toMutableList()

    fun addCard(card: Card) {
        cards.add(card)
    }

    fun cardSum(): Number {
        return cards.sumOf { it.number.value.toInt() }
    }
}
