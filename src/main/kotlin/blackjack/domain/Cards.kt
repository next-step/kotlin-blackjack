package blackjack.domain

data class Cards(
    private val cards: MutableList<Card> = mutableListOf()
) {
    val score: Int
        get() {
            var sum = 0
            cards.forEach { card ->
                sum += if (card.denomination.isAce()) {
                    getAce(sum)
                } else {
                    card.denomination.score
                }
            }
            return sum
        }

    private fun getAce(sum: Int) = if (sum < ACE_THRESHOLD) HIGHER_ACE else LOWER_ACE

    fun get(): List<Card> = cards

    fun add(card: Card) {
        cards.add(card)
    }

    companion object {
        private const val LOWER_ACE = 1
        private const val HIGHER_ACE = 11
        private const val ACE_THRESHOLD = 10
    }
}
