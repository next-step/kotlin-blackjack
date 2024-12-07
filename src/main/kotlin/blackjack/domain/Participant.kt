package blackjack.domain

import blackjack.domain.ResultStatistics.Constant.DRAW_STATISTICS
import blackjack.domain.ResultStatistics.Constant.LOSE_STATISTICS
import blackjack.domain.ResultStatistics.Constant.WIN_STATISTICS

sealed class Participant(private val hand: Hand) {
    val isBust: Boolean
        get() = hand.isBust
    val totalCards: Deck
        get() = Deck(hand.totalCards)
    val bustGap: Int
        get() = hand.bustGap()

    fun score(): Score {
        return hand.score
    }

    fun receive(cards: Deck) {
        hand.add(cards.values())
    }

    fun matchToStatistics(other: Participant): ResultStatistics {
        return when (matchOf(other)) {
            MatchType.WIN -> WIN_STATISTICS
            MatchType.LOSE -> LOSE_STATISTICS
            else -> DRAW_STATISTICS
        }
    }

    private fun matchOf(other: Participant): MatchType {
        return when {
            isBust -> MatchType.LOSE
            other.isBust -> MatchType.WIN
            else -> MatchType.evaluate(hand.bustGap(), other.hand.bustGap())
        }
    }
}
