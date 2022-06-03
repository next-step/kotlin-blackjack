package blackjack.domain

class PlayerCards(list: List<Card> = emptyList()) {
    private val _cards = list.toMutableList()
    val cards: List<Card> get() = _cards

    val score: Int get() = calculateScore()
    val size: Int get() = _cards.size

    fun addCard(card: Card) {
        _cards.add(card)
    }

    private fun calculateScore(): Int {
        var sum = _cards.sumOf { it.score.value }
        if (hasAceCard() && sum <= ACE_INCREASE_THRESHOLD) {
            sum += ACE_INCREASE_SCORE
        }
        return sum
    }

    private fun hasAceCard() = _cards.any { it is AceCard }

    companion object {
        private const val ACE_INCREASE_SCORE = 10
        private const val ACE_INCREASE_THRESHOLD = 11
    }
}
