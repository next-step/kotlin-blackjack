package blackjack.domain.card

data class Deck(
    private val cards: MutableList<Card> = mutableListOf()
) {
    fun add(card: Card) = cards.add(card)

    fun contains(card: Card) = cards.contains(card)

    fun getCards() = cards

    fun getScore(): Int {
        val deckForCalculate = cards.toMutableList().sortedByDescending { it.getScore() }

        var score = 0
        for (card in deckForCalculate) {
            score += card.getScore(score)
        }

        return score
    }
}
