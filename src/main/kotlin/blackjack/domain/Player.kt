package blackjack.domain

class Player(val name: String) {
    val cards = Cards()

    fun offer(card: Card) {
        cards.add(card)
    }

    fun offer(servedCards: Cards) {
        cards.addAll(servedCards)
    }

    fun getMaxSumLessThan21(): Int {
        return cards.possibleResults
            .filter { it < BLACKJACK_LIMIT }
            .maxOf { it }
    }

    companion object {
        private const val BLACKJACK_LIMIT = 21
    }
}
