package blackjack.domain

data class Cards(val value: MutableList<Card> = mutableListOf()) {
    fun addCard(card: Card) {
        value.add(card)
    }

    fun score(): Int {
        var score = value.sumOf { it.character.score }
        if (value.any { it.isAce() } && (score + ACE_PLUS_SCORE) <= BlackJack.BLACKJACK_MAX_SCORE) {
            score += ACE_PLUS_SCORE
        }
        return score
    }

    companion object {
        private const val ACE_PLUS_SCORE = 10
    }
}
