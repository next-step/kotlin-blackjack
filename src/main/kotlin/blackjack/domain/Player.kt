package blackjack.domain

class Player(val name: String) {
    private val _hand: MutableList<Card> = mutableListOf()
    val hand: List<Card> = _hand

    fun score(): Int {
        val score = hand.sumOf { it.denomination.score }

        if (!hasAce()) {
            return score
        }

        return calculateScoreWithAce(score)
    }

    fun addCard(card: Card) {
        _hand.add(card)
    }

    private fun calculateScoreWithAce(score: Int): Int {
        if (score <= 11) {
            return score + 10
        }

        return score
    }

    private fun hasAce(): Boolean {
        return hand.any { it.denomination == Denomination.ACE }
    }
}
