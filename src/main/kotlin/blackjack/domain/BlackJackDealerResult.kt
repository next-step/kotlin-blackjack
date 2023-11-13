package blackjack.domain

class BlackJackDealerResult(
    private val dealer: Dealer,
    private val winLoseMoneyBy: Map<WinLose, Money>
) {

    val name: String get() = dealer.name
    val cards: Cards get() = dealer.cards
    val score: Score get() = cards.score()
    val firstOpenCards: Cards get() = dealer.firstOpenCards()

    fun winLoseTotalMoney(): Money {
        return winLoseMoneyBy.values.reduce { acc, money -> acc + money }.opposite()
    }
}
