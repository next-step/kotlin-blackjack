package blackjack.view

import blackjack.domain.Dealer
import blackjack.domain.Score
import blackjack.domain.Suit
import blackjack.domain.WinLose

class BlackJackDealerResult(val dealer: Dealer) {

    val firstCard: String
        get() = "${dealer.cardSet.first().rank.rankName}${dealer.cardSet.first().suit.suitName()}"
    val cards: String
        get() = dealer.cardSet.joinToString(", ") { card ->
            "${card.rank.rankName}${card.suit.suitName()}"
        }
    val score: Score get() = dealer.cards.score()

    fun winLose(playerScore: List<Score>): String {
        val winLoseResults = playerScore.map { score.winLose(it) }
        val winLoseCount = winLoseResults.groupingBy { it }.eachCount()

        return "${winLoseCount[WinLose.WIN] ?: 0}${WinLose.WIN.name()} " +
            "${winLoseCount[WinLose.LOSE] ?: 0}${WinLose.LOSE.name()} " +
            "${winLoseCount[WinLose.DRAW] ?: 0}${WinLose.DRAW.name()}"
    }

    private fun Suit.suitName(): String {
        return when (this) {
            Suit.SPADE -> "스페이드"
            Suit.HEART -> "하트"
            Suit.DIAMOND -> "다이아몬드"
            Suit.CLUB -> "클로버"
        }
    }

    private fun WinLose.name(): String {
        return when (this) {
            WinLose.WIN -> "승"
            WinLose.LOSE -> "패"
            WinLose.DRAW -> "무"
        }
    }
}
