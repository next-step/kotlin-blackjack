package blackjack.view

import blackjack.domain.Player
import blackjack.domain.Score
import blackjack.domain.Suit
import blackjack.domain.WinLose

class BlackJackPlayerResult(
    private val player: Player,
) {
    val playerName: String get() = player.name
    val cards: String
        get() = player.cardSet.joinToString(", ") { card ->
            "${card.rank.rankName}${card.suit.suitName()}"
        }
    val score: Score get() = player.cards.score()

    fun winLose(dealerScore: Score): String {
        return score.winLose(dealerScore).name()
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
