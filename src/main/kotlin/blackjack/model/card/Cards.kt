package blackjack.model.card

data class Cards(
    val cards: MutableList<Card> = mutableListOf(),
) {
    val size
        get() = cards.size

    val sumOfScore
        get(): CardScore {
            if (cards.isEmpty()) {
                return CardScore.ZERO
            }
            return cards.map { it.score }
                .reduce { totalScore, score -> totalScore + score }
        }

    fun shuffle() = cards.shuffle()

    fun addOne(card: Card) {
        cards.add(card)
    }

    fun removeOne(): Card {
        validateNotEmpty()
        return cards.removeAt(0)
    }

    private fun validateNotEmpty() = require(size > EMPTY_CARDS_SIZE) { "카드 개수가 0개 입니다." }

    companion object {
        private const val EMPTY_CARDS_SIZE = 0
    }
}
