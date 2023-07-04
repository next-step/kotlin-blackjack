package blackjack.domain.card

data class Cards(
    val cards: List<Card>
) {
    private var index = 0

    init {
        validateDuplicate()
    }

    private fun validateDuplicate() {
        require(cards.size == cards.distinct().count())
    }

    fun next() = cards[index++]
}
