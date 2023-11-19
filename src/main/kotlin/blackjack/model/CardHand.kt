package blackjack.model

class CardHand(cards: List<Card>) {
    var cards: List<Card> = cards
        private set

    fun addCard(card: Card) {
        val newCards = cards.toMutableList()
        newCards.add(card)
        cards = newCards.toList()
    }

    fun getTotalScore(): Int = getCardValues().fold(0) { total, it ->
        when (it.cardValue) {
            "A" -> getAceScore(total)
            "J", "Q", "K" -> total + JQA_SCORE
            else -> total + (it.cardValue.toIntOrNull() ?: 0)
        }
    }

    private fun getCardValues(): List<CardValue> = cards.map { card -> card.cardValue }
        .sortedWith(compareBy { if (it.cardValue == "A") Int.MAX_VALUE else 0 })

    private fun getAceScore(total: Int): Int {
        val highScore = total + CardValue.ACE_HIGH_SCORE
        val lowScore = total + CardValue.ACE_LOW_SCORE

        return if (kotlin.math.abs(BLACKJACK - highScore) < kotlin.math.abs(BLACKJACK - lowScore)) {
            highScore
        } else {
            lowScore
        }
    }

    companion object{
        const val BLACKJACK = 21
        const val JQA_SCORE = 10
    }
}
