package blackjack.domain

class PlayerCards(list: List<Card> = emptyList()) {
    private val _cards = list.toMutableList()
    val cards: List<Card> get() = _cards

    val score: Score get() = calculateScore()
    val size: Int get() = _cards.size

    fun addCard(card: Card) {
        _cards.add(card)
    }

    fun isBust(): Boolean = score > CARD_BUST_THRESHOLD

    private fun calculateScore(): Score {
        var sum = _cards.fold(Score(0)) { acc, card -> acc + card.score }
        if (hasAceCard() && sum <= ACE_INCREASE_THRESHOLD) {
            sum += ACE_INCREASE_SCORE
        }
        return sum
    }

    private fun hasAceCard() = _cards.any { it is AceCard }

    companion object {
        private val ACE_INCREASE_SCORE = Score(10)
        private val ACE_INCREASE_THRESHOLD = Score(11)

        private val CARD_BUST_THRESHOLD = Score(21)
    }
}
