package blackjack.model.card

class CardScore(
    val score: Int,
    val otherScore: Int,
) {

    fun plus(other: CardScore): CardScore {
        val sumOfScore = score.plus(other.score)
        val sumOfOtherScore = otherScore.plus(other.otherScore)
        return CardScore(sumOfScore, sumOfOtherScore)
    }
}
