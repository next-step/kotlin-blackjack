package blackjack.view

import blackjack.domain.Card
import blackjack.domain.Dealer
import blackjack.domain.Score
import blackjack.domain.WinLose

class BlackJackDealerResult(private val dealer: Dealer) : BlackJackGameResult {

    override val name: String get() = dealer.name
    override val cardSet: Set<Card> get() = dealer.cardSet
    override val cards get() = dealer.cards

    val firstCard: String
        get() = "${dealer.cardSet.first().rank.rankName}${dealer.cardSet.first().suit.suitName()}"

    fun winLose(playerScore: List<Score>): String {
        val winLoseResults = playerScore.map { score.winLose(it) }
        val winLoseCount = winLoseResults.groupingBy { it }.eachCount()

        return "${winLoseCount[WinLose.WIN] ?: 0}${WinLose.WIN.name()} " +
            "${winLoseCount[WinLose.LOSE] ?: 0}${WinLose.LOSE.name()} " +
            "${winLoseCount[WinLose.DRAW] ?: 0}${WinLose.DRAW.name()}"
    }
}
