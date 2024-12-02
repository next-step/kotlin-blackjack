package blackjack.domain

data class PlayerCardDeck(
    private val cards: MutableList<Card> = mutableListOf(),
    private val aceCards: MutableList<Card> = mutableListOf(),
) {
    val allCards: List<Card>
        get() = aceCards + cards

    fun addCard(card: Card) {
        when (card.number) {
            CardNumber.ACE -> aceCards.add(card)
            else -> cards.add(card)
        }
    }

    fun findPossibleCardScores(): List<Int> {
        val cardsSum = cards.sumOf { it.number.value }
        if (aceCards.isEmpty()) return listOf(cardsSum)
        val acesSums =
            aceCards
                .map { listOf(ACE_ONE, ACE_ELEVEN) }
                .reduce { acc, list ->
                    acc.flatMap { sum -> list.map { sum + it } }
                }.distinct()
                .sorted()

        return acesSums.map { it + cardsSum }
    }

    companion object {
        private const val ACE_ONE = 1
        private const val ACE_ELEVEN = 11
    }
}
