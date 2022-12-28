package blackjack.domain

import blackjack.util.WinningAmountCalculator

data class Player(
    val name: String,
    val playingCards: PlayingCards = PlayingCards(),
    val bettingAmount: Int = 0,
) {
    fun addCard(deal: Card): Player {
        playingCards.addOne(deal)
        return this
    }

    fun addCard(cards: Set<Card>): Player {
        playingCards.addAll(cards)
        return this
    }

    fun cardPoint() = playingCards.calculatePoint()
    fun blackJack() = playingCards.cards.size == 2 && playingCards.calculatePoint() == Point.BLACK_JACK

    fun flip(dealer: Dealer): WinningAmountCalculator {
        return when {
            bust() -> WinningAmountCalculator.lose(bettingAmount)
            dealer.bust() -> WinningAmountCalculator.win(bettingAmount)
            blackJack() && !dealer.blackJack() -> WinningAmountCalculator.blackJack(bettingAmount)
            dealer.blackJack() && !blackJack() -> WinningAmountCalculator.lose(bettingAmount)
            cardPoint() > dealer.cardPoint() -> WinningAmountCalculator.win(bettingAmount)
            cardPoint() < dealer.cardPoint() -> WinningAmountCalculator.lose(bettingAmount)
            else -> WinningAmountCalculator.tie()
        }
    }

    fun bust(): Boolean = playingCards.bust()
    fun firstCard(): Set<Card> = playingCards.firstCard()

}
