package model

private const val BLACKJACK_MAX_SCORE = 21

private const val ACE_SPECIAL_MINUS_SCORE = 10

class PokerScore(private val cards: Cards) {
    val score: Int = score()

    private fun score(): Int {
        val aceCard = cards.cards.find { it.pokerNumber == PokerNumber.find("A") } != null
        val score = cards.cards.sumOf { it.pokerNumber.score }
        return specialAceRule(aceCard, score)
    }

    private fun specialAceRule(aceCard: Boolean, score: Int): Int {
        if (aceCard && score > BLACKJACK_MAX_SCORE) {
            return score - ACE_SPECIAL_MINUS_SCORE
        }
        return score
    }
}
