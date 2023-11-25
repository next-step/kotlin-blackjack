package blackjack.domain.rule

import blackjack.domain.card.Card
import blackjack.domain.card.CardCharacter

class DefaultScoringRule : ScoringRule {

    override fun sumAll(cards: List<Card>): Int {
        val firstSumUsingAceTo11 = cards.sumOf { CARD_SCORE_BORD.getValue(it.character).first }
        if (firstSumUsingAceTo11 > THRESHOLD_SCORE) {
            return cards.sumOf { CARD_SCORE_BORD.getValue(it.character).second }
        }

        return firstSumUsingAceTo11
    }

    override fun isOverThreshold(score: Int): Boolean {
        return score > THRESHOLD_SCORE
    }

    companion object {
        const val THRESHOLD_SCORE = 21
        val CARD_SCORE_BORD: Map<CardCharacter, Pair<Int, Int>> = mapOf(
            CardCharacter.ACE to Pair(11, 1),
            CardCharacter.TWO to Pair(2, 2),
            CardCharacter.THREE to Pair(3, 3),
            CardCharacter.FOUR to Pair(4, 4),
            CardCharacter.FIVE to Pair(5, 5),
            CardCharacter.SIX to Pair(6, 6),
            CardCharacter.SEVEN to Pair(7, 7),
            CardCharacter.EIGHT to Pair(8, 8),
            CardCharacter.NINE to Pair(9, 9),
            CardCharacter.TEN to Pair(10, 10),
            CardCharacter.JACK to Pair(10, 10),
            CardCharacter.QUEEN to Pair(10, 10),
            CardCharacter.KING to Pair(10, 10),
        )
    }
}
