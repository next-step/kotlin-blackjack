package blackjack.view

import blackjack.domain.Card
import blackjack.domain.Cards
import blackjack.domain.Score
import blackjack.domain.Suit
import blackjack.domain.WinLose

interface BlackJackGameResult {
    val name: String
    val cardSet: Set<Card>
    val cards: Cards
    val score: Score get() = cards.score()
    val cardDisplay: String
        get() = cardSet.joinToString(", ") { card ->
            "${card.rank.rankName}${card.suit.suitName()}"
        }

    fun Suit.suitName(): String {
        return when (this) {
            Suit.SPADE -> "스페이드"
            Suit.HEART -> "하트"
            Suit.DIAMOND -> "다이아몬드"
            Suit.CLUB -> "클로버"
        }
    }

    fun WinLose.name(): String {
        return when (this) {
            WinLose.WIN -> "승"
            WinLose.LOSE -> "패"
            WinLose.DRAW -> "무"
        }
    }
}
