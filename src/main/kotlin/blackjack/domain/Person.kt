package blackjack.domain

data class Person(
    val name: String,
    val cards: Cards = Cards()
) {
    fun addCard(card: Card) {
        cards.add(card)
    }

    fun getScore(): Int {
        return cards.calculate()
    }
}
