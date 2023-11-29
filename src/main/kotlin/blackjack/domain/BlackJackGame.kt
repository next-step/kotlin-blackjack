package blackjack.domain

object BlackJackGame {
    const val MAX_SCORE = 21
    private const val ADDITIONAL_ACE_SCORE = 10

    fun score(cards: CardList): Int {
        var score = cards.cards.sumOf { it.score() }
        if (cards.hasAce() && score + ADDITIONAL_ACE_SCORE <= MAX_SCORE) {
            score += ADDITIONAL_ACE_SCORE
        }
        return score
    }
}
