package blackjack.domain

class Deck(
    private val cards: Cards = Cards.fullCards()
) {
    val quantity: Int
        get() = cards.size

    fun draw(): Card {
        if (quantity == 0) {
            throw IllegalStateException("카드가 모두 소진되었습니다.")
        }

        return cards.draw()
    }
}
