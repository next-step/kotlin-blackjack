package blackjack.domain.model

import blackjack.domain.interfaces.Drawable

data class Dealer(
    val name: Name,
    val cards: Cards
): Drawable {
    override fun draw(card: Card) = cards + card
    fun shouldDraw(maxNumber: Int): Boolean = cards.sum(maxNumber).value < Score.from(maxNumber).value

    fun isBust() = cards.sum().value < Score.from().value

    companion object {
        fun from(name: Name, cards: Cards = Cards.empty()): Dealer = Dealer(name, cards)
    }
}
