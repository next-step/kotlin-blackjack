package blackjack.model

object ResultDealer {
    private const val GOAL = 21

    fun getCardValues(cardDeck: CardDeck): List<CardValue> = cardDeck.cards.map { card -> card.cardValue }
        .sortedWith(compareBy { if (it.cardValue == "A") Int.MAX_VALUE else 0 })

    fun getTotalScore(cardNumbers: List<CardValue>): Int = cardNumbers.fold(0) { total, it ->
        when (it.cardValue) {
            "A" -> getAceScore(total)
            "J", "Q", "K" -> total + 10
            else -> total + (it.cardValue.toIntOrNull() ?: 0)
        }
    }

    private fun getAceScore(total: Int): Int {
        val highScore = total + CardValue.ACE_HIGH_SCORE
        val lowScore = total + CardValue.ACE_LOW_SCORE

        return if (kotlin.math.abs(GOAL - highScore) < kotlin.math.abs(GOAL - lowScore)) {
            highScore
        } else {
            lowScore
        }
    }
}
