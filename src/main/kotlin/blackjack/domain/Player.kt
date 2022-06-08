package blackjack.domain

class Player(val name: String) {
    private val _cards = mutableListOf<Card>()
    val cards
        get() = _cards.toList()

    fun offer(card: Card) {
        _cards.add(card)
    }

    fun offer(servedCards: List<Card>) {
        _cards.addAll(servedCards)
    }

    fun getMaxSumLessThan21(): Int {
        return ResultCalculator().getAllPossibleResults(cards)
            .filter { it < BLACKJACK_LIMIT }
            .maxOf { it }
    }

    companion object {
        private const val BLACKJACK_LIMIT = 21
    }
}
