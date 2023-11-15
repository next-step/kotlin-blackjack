package blackjack.domain

class BlackJackDealerResult(
    private val dealer: Dealer,
    val winLoseMoney: Money
) {

    val name: String get() = dealer.name
    val cards: Cards get() = dealer.cards
    val score: Score get() = cards.score()
    val firstOpenCards: Cards get() = dealer.firstOpenCards()
}
