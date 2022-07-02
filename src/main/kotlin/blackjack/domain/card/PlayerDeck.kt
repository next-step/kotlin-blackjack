package blackjack.domain.card

class PlayerDeck(cards: List<Card> = emptyList()) {

    private val _cards: MutableList<Card> = cards.toMutableList()

    fun addCard(card: Card) {
        _cards.add(card)
    }

    val cards: List<Card>
        get() = _cards.toList()

    fun calculateScore(): Int {
        var sumOf = cards.sumOf { it.cardSymbol.score }
        val aceCount = cards.count { it.cardSymbol == CardSymbol.ACE }

        repeat(aceCount) {
            if (sumOf + CORRECTION_NUMBER <= BLACKJACK) {
                sumOf += CORRECTION_NUMBER
            }
        }

        return sumOf
    }

    companion object {
        private const val CORRECTION_NUMBER = 10
        private const val BLACKJACK = 21
    }
}
