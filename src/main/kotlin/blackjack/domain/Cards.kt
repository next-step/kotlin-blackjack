package blackjack.domain

private const val GAME_MAX_SCORE = 21

private const val ACE_PLUS_SCORE = 10

data class Cards(val cards: MutableList<Card> = mutableListOf()) {
    fun addCard(card: Card) {
        cards.add(card)
    }

    fun score(): Int {
        var score = cards.sumOf { it.character.score }
        if (cards.find { it.character == Character.A } != null && (score + ACE_PLUS_SCORE) <= GAME_MAX_SCORE) {
            score += ACE_PLUS_SCORE
        }
        return score
    }
}
