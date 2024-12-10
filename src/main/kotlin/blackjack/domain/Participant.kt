package blackjack.domain

import blackjack.domain.ResultStatistics.Constant.DRAW_STATISTICS
import blackjack.domain.ResultStatistics.Constant.LOSE_STATISTICS
import blackjack.domain.ResultStatistics.Constant.WIN_STATISTICS

sealed class Participant(private val hand: Hand, private var _bettingMoney: Money = Money(0)) {
    val bettingMoney: Money
        get() = _bettingMoney
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

    fun betting(other: Money) {
        this._bettingMoney = this._bettingMoney.plus(other)
    }


    fun evenMoney(): Money {
        return _bettingMoney.evenMoney()
    }

    fun isBlackJack() : Boolean {
        return hand.isBackJack()
    }

    fun profit(other: Money): Money {
        return other - _bettingMoney
    }

    private fun matchOf(other: Participant): MatchType {
        return when {
            isBust -> MatchType.LOSE
            other.isBust -> MatchType.WIN
            else -> MatchType.evaluate(hand.bustGap(), other.hand.bustGap())
        }
    }
}
