package blackjack.domain.card

import blackjack.BlackJackGame

class Cards(
    private val cards: MutableList<Card> = mutableListOf()
) {
    fun addCard(card: Card) {
        cards += card
    }

    fun getScore(): Int {
        var score = cards.sumOf { it.number.score }

        if (isAceAvailable(score)) {
            score += CardNumber.ACE_ADDITIONAL_SCORE
        }
        return score
    }

    private fun isAceAvailable(score: Int): Boolean {
        return cards.any { it.isAce() }
                && (score + CardNumber.ACE_ADDITIONAL_SCORE) <= BlackJackGame.MAX_SCORE
    }

    override fun toString(): String {
        return cards.joinToString()
    }

}
