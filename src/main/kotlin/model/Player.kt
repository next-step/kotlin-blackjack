package model

open class Player(
    val name: String,
    val cards: Cards = Cards()
) {
    fun hit(card: Card) = cards.add(card)
}
