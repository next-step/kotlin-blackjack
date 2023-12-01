package blackjack.domain.model

import blackjack.domain.interfaces.Drawable

data class Gambler(
    val name: Name,
    val cards: Cards
) : Drawable {

    override fun draw(card: Card) = cards + card
    fun shouldDraw(maxNumber: Int): Boolean = cards.sum(maxNumber).value < Score.from(maxNumber).value
    fun isBust() = cards.sum(BLACK_JACK_NUMBER).value > Score.from(BLACK_JACK_NUMBER).value
    fun winLoseDraw(dealer: Dealer): WinLoseDraw {
        return when {
            isBust() -> WinLoseDraw.LOSE
            dealer.isBust() || cards.sum().value > dealer.cards.sum().value -> WinLoseDraw.WIN
            cards.sum().value < dealer.cards.sum().value -> WinLoseDraw.LOSE
            else -> WinLoseDraw.DRAW
        }
    }

    companion object {
        private const val BLACK_JACK_NUMBER = 21
        fun from(name: Name, cards: Cards = Cards.empty()): Gambler = Gambler(name, cards)
    }
}
