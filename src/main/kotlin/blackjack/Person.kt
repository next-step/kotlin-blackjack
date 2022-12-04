package blackjack

data class Person(
    val name: String,
    val cards: Cards = Cards()
) {
    override fun toString(): String {
        return "${name}카드: $cards"
    }

    fun addCard(card: Card) {
        cards.add(card)
    }

    fun getScore(): Int {
        return cards.calculate()
    }
}
