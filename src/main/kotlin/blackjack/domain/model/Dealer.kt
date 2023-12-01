package blackjack.domain.model

import blackjack.domain.interfaces.Drawable

data class Dealer(
    val name: Name,
    val cards: Cards,
) : Drawable {

    override fun draw(card: Card) = cards + card
    fun shouldDraw(): Boolean = cards.sum(BLACK_JACK_NUMBER).value <= Score.from(SHOULD_DRAW_NUMBER).value

    fun isBust() = cards.sum(BLACK_JACK_NUMBER).value > Score.from(BLACK_JACK_NUMBER).value

    companion object {
        private const val SHOULD_DRAW_NUMBER = 16
        private const val BLACK_JACK_NUMBER = 21
        fun from(name: Name, cards: Cards = Cards.empty()): Dealer = Dealer(name, cards)
    }
}
