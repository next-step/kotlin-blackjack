package blackjack.domain.card

data class Deck(
    private val cards: MutableList<Card> = mutableListOf()
) {
    fun add(card: Card) {
        cards.add(card)
    }

    fun getScore() = sumScore(cards.toMutableList().sortedByDescending { it.getScore() })

    private fun sumScore(cards: List<Card>): Int {
        var score = 0
        for (card in cards) {
            score += card.getScore(score)
        }
        return score
    }

    fun contains(card: Card) = cards.contains(card)

    fun getCards() = cards.toList()
}
