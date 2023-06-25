package blackjack.domain

data class Cards(val cards: MutableList<Card> = mutableListOf()) {
    fun addCard(card: Card) {
        cards.add(card)
    }

    fun score(): Int {
        var score = cards.sumOf { it.character.score }
        if (cards.find { it.character == Character.A } != null && (score + ACE_PLUS_SCORE) <= BlackJack.BLACKJACK_MAX_SCORE) {
            score += ACE_PLUS_SCORE
        }
        return score
    }

    companion object {
        private const val ACE_PLUS_SCORE = 10
    }
}
