package blackjack.domain.card

import blackjack.domain.card.component.Denomination

object Score {
    const val BLACK_JACK = 21

    fun from(cards: List<Card>): Int {
        val totalScore = cards.sumBy { it.getScore() }
        return if (cards.hasAce()) sumScoresWithAce(totalScore)
        else totalScore
    }

    private fun List<Card>.hasAce(): Boolean {
        return this.find { it.isAce() } != null
    }

    private fun sumScoresWithAce(totalScore: Int): Int {
        val totalScoreWithAceBonus = totalScore + Denomination.MAX_SCORE
        return if (totalScoreWithAceBonus > BLACK_JACK) totalScore
        else totalScoreWithAceBonus
    }
}
