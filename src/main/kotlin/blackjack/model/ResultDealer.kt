package blackjack.model

object ResultDealer {
    private const val BLACKJACK = 21
    private const val JQA_SCORE = 10

    private fun getCardValues(cardDeck: CardDeck): List<CardValue> = cardDeck.cards.map { card -> card.cardValue }
        .sortedWith(compareBy { if (it.cardValue == "A") Int.MAX_VALUE else 0 })

    fun getTotalScore(cardDeck: CardDeck): Int = getCardValues(cardDeck).fold(0) { total, it ->
        when (it.cardValue) {
            "A" -> getAceScore(total)
            "J", "Q", "K" -> total + JQA_SCORE
            else -> total + (it.cardValue.toIntOrNull() ?: 0)
        }
    }

    private fun getAceScore(total: Int): Int {
        val highScore = total + CardValue.ACE_HIGH_SCORE
        val lowScore = total + CardValue.ACE_LOW_SCORE

        return if (kotlin.math.abs(BLACKJACK - highScore) < kotlin.math.abs(BLACKJACK - lowScore)) {
            highScore
        } else {
            lowScore
        }
    }

}
