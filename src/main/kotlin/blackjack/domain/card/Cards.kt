package blackjack.domain.card

class Cards(
    private val cards: MutableList<Card> = mutableListOf()
) {
    fun addCard(card: Card) {
        cards += card
    }

    fun getScore(): Int {
        var score = cards.sumOf { it.number.score }

        if (cards.any { it.isAce() } && (score + CardNumber.ACE_ADDITIONAL_SCORE) <= 21) {
            score += CardNumber.ACE_ADDITIONAL_SCORE
        }
        return score
    }

    override fun toString(): String {
        return cards.joinToString()
    }

}
