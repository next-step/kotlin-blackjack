package blackjack.domain.model

data class Gambler(
    val name: Name,
    val cards: Cards
) {
    fun receive(card: Card) = cards + card
    fun shouldDraw(maxNumber: Int): Boolean = cards.sum(maxNumber).value < Score.from(maxNumber).value
    companion object {
        fun from(name: Name, cards: Cards = Cards.empty()): Gambler = Gambler(name, cards)
    }
}
