package blackjack.domain

data class Player(
    val name: String,
    val playingCards: PlayingCards = PlayingCards(),
    val bettingAmount: BettingAmount = BettingAmount(0),
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

    fun flip(dealer: Dealer): Int {
        return when {
            bust() -> bettingAmount.lose()
            dealer.bust() -> bettingAmount.win()
            blackJack() && !dealer.blackJack() -> bettingAmount.blackJack()
            dealer.blackJack() && !blackJack() -> bettingAmount.lose()
            cardPoint() > dealer.cardPoint() -> bettingAmount.win()
            cardPoint() < dealer.cardPoint() -> bettingAmount.lose()
            else -> bettingAmount.tie()
        }
    }

    fun bust(): Boolean = playingCards.bust()
    fun firstCard(): Set<Card> = playingCards.firstCard()

}
