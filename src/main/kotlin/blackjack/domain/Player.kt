package blackjack.domain

class Player(val name: String, private val _cards: MutableList<Card> = mutableListOf()) {
    val cards: List<Card>
        get() = _cards

    fun score(): Int {
        var score = _cards.sumOf { it.score() }
        if (hasAce() && score + ADDITIONAL_ACE_SCORE <= MAX_SCORE) {
            score += ADDITIONAL_ACE_SCORE
        }
        return score
    }

    fun addCard(newCard: Card) {
        _cards.add(newCard)
    }

    fun addCard(newCards: List<Card>) {
        _cards.addAll(newCards)
    }

    fun canDraw(): Boolean {
        return score() < MAX_SCORE
    }

    private fun hasAce(): Boolean {
        return _cards.any(Card::isAce)
    }

    companion object {
        private const val MAX_SCORE = 21
        private const val ADDITIONAL_ACE_SCORE = 10
    }
}
