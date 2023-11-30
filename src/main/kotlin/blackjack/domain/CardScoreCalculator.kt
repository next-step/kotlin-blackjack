package blackjack.domain

object CardScoreCalculator {
    const val BLACKJACK = 21
    const val DEALER_SCORE_THRESHOLD = 16

    fun isBlackjack(cards: Cards): Boolean {
        return calcScore(cards) == BLACKJACK
    }

    fun isBust(cards: Cards): Boolean {
        return calcScore(cards) > BLACKJACK
    }

    fun isUnderScore(cards: Cards, threshold: Int): Boolean {
        return calcScore(cards) < threshold
    }

    fun isOverScore(cards: Cards, threshold: Int): Boolean {
        return calcScore(cards) > threshold
    }

    fun isOverScore(cards1: Cards, cards2: Cards): Boolean {
        return calcScore(cards1) > calcScore(cards2)
    }

    fun calcScore(cards: Cards): Int {
        val denominations = cards.values.map { it.denomination }
        var score = denominations.sumOf { it.score }
        var numOfAce = denominations.count { it == Denomination.ACE }
        while (numOfAce > 0) {
            if (score > BLACKJACK) {
                score -= (Denomination.ACE.score - Denomination.ACE.aceScore)
            }
            numOfAce -= 1
        }
        return score
    }
}

fun Cards.toScore(): Int {
    return CardScoreCalculator.calcScore(this)
}
