import card.CardNumber
import card.PlayingCard

object BlackJackCalculator {

    private const val ADDITIONAL_SCORE = 10
    private const val NO_ADDITIONAL_SCORE = 0
    private const val ACE_ADDITIONAL_SCORE_THRESHOLD = 12

    fun calculate(cardList: List<PlayingCard>): Int {
        var result = cardList.sumOf { it.getPoint() }
        cardList.filter { it.getCardNumber() == CardNumber.ACE }
            .forEach { _ -> result += addCalculateAce(result) }

        return result
    }

    private fun addCalculateAce(sumValue: Int): Int {
        return if (sumValue < ACE_ADDITIONAL_SCORE_THRESHOLD) {
            ADDITIONAL_SCORE
        } else {
            NO_ADDITIONAL_SCORE
        }
    }
}
