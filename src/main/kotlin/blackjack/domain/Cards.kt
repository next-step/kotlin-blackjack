package blackjack.domain

class Cards(private val cards: MutableList<Card> = mutableListOf()) {
    val elements: List<Card>
        get() = cards.toList()

    fun calculateTotalScore(): Int {
        val sumOfNotAceScore =
            cards.doNotFilter { card -> card.isAce }
                .sumOf { card -> card.scores.first() }

        return cards.filter { card -> card.isAce }
            .fold(sumOfNotAceScore) { accumulatedScore, card ->
                accumulatedScore + determineAceScore(accumulatedScore, card)
            }
    }

    private fun MutableList<Card>.doNotFilter(predicate: (Card) -> Boolean): List<Card> {
        return filter { card -> predicate(card).not() }
    }

    private fun determineAceScore(
        accumulatedScore: Int,
        card: Card,
    ): Int {
        if (accumulatedScore >= BlackjackRule.BLACKJACK_SCORE) {
            return card.scores.min()
        }

        return card.scores
            .filter { score -> score + accumulatedScore <= BlackjackRule.BLACKJACK_SCORE }
            .max()
    }

    fun addAll(vararg cards: Card) {
        this.cards.addAll(cards)
    }

    fun add(card: Card) {
        cards.add(card)
    }

    override fun toString(): String {
        return "Cards(cards=$cards)"
    }
}
