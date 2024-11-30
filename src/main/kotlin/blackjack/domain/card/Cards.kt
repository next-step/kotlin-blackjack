package blackjack.domain.card

data class Cards(
    private val _cards: MutableList<Card> = mutableListOf(),
) {
    val cards: List<Card>
        get() = _cards

    fun add(card: Card) {
        _cards.add(card)
    }

    fun sum(): Int {
        var totalSum = cards.filter { it.rank != CardRank.Ace }.sumOf { it.rank.value }

        cards.filter { it.rank == CardRank.Ace }.forEach {
            totalSum += getAceValue(it.rank as CardRank.Ace, totalSum)
        }

        return totalSum
    }

    private fun getAceValue(ace: CardRank.Ace, currentSum: Int): Int {
        return if (currentSum + ace.toEleven() <= MAX_CARDS_SUM) {
            ace.toEleven()
        } else {
            ace.value
        }
    }

    companion object {
        private const val MAX_CARDS_SUM = 21
    }

}
