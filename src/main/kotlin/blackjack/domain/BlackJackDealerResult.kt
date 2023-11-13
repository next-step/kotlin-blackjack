package blackjack.domain

class BlackJackDealerResult(
    private val dealer: Dealer,
    private val winLoseCountBy: Map<WinLose, Int>
) {

    val name: String get() = dealer.name
    val cards: Cards get() = dealer.cards
    val score: Score get() = cards.score()
    val firstOpenCards: Cards get() = dealer.firstOpenCards()
    fun winLoseCountBy(winLose: WinLose): Int {
        return winLoseCountBy[winLose] ?: 0
    }
}
