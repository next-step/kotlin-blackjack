package blackjack.view

import blackjack.model.Card
import blackjack.model.Denomination
import blackjack.model.MatchResult
import blackjack.model.Suit

class KoreanMessageSource {
    private val suitNameMap = hashMapOf(
        Suit.SPADE to "스페이드",
        Suit.HEART to "하트",
        Suit.DIAMOND to "다이아몬드",
        Suit.CLOVER to "클로버"
    )

    private val denomNameMap = hashMapOf(
        Denomination.ACE to "A",
        Denomination.TWO to "2",
        Denomination.THREE to "3",
        Denomination.FOUR to "4",
        Denomination.FIVE to "5",
        Denomination.SIX to "6",
        Denomination.SEVEN to "7",
        Denomination.EIGHT to "8",
        Denomination.NINE to "9",
        Denomination.TEN to "10",
        Denomination.JACK to "J",
        Denomination.QUEEN to "Q",
        Denomination.KING to "K"
    )

    private val matchResultMap = hashMapOf(
        MatchResult.WIN to "승",
        MatchResult.LOSE to "패",
        MatchResult.PUSH to "무",
    )

    fun nameOf(matchResult: MatchResult): String {
        return matchResultMap[matchResult] ?: matchResult.name
    }

    fun nameOf(card: Card): String {
        return nameOf(card.denomination) + nameOf(card.suit)
    }

    private fun nameOf(suit: Suit): String {
        return suitNameMap[suit] ?: suit.name
    }

    private fun nameOf(denomination: Denomination): String {
        return denomNameMap[denomination] ?: denomination.name
    }
}
