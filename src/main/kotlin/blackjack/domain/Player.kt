package blackjack.domain

sealed class Player(
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
