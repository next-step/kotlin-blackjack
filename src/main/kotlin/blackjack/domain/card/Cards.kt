package blackjack.domain.card

import blackjack.domain.game.BlackJack

@JvmInline
value class Cards(val values: MutableList<Card> = mutableListOf()) {
    fun addCard(card: Card) {
        values.add(card)
    }

    fun score(): Int {
        var score = values.sumOf { it.character.score }
        if (values.any { it.isAce() } && (score + ACE_PLUS_SCORE) <= BlackJack.BLACKJACK_MAX_SCORE) {
            score += ACE_PLUS_SCORE
        }
        return score
    }

    companion object {
        private const val ACE_PLUS_SCORE = 10
    }
}
