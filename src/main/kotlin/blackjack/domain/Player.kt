package blackjack.domain

class Player(val name: String, val hand: MutableList<Card> = mutableListOf()) {
    fun score(): Int {
        val score = hand.sumOf { it.denomination.score }

        if (!hasAce()) {
            return score
        }

        return calculateScoreWithAce(score)
    }

    fun draw(deck: Deck) {
        hand.add(deck.draw())
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
