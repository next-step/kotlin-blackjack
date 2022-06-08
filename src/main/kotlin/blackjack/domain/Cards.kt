package blackjack.domain

class Cards(initialCards: List<Card> = emptyList()) {
    val possibleResults
        get() = getAllPossibleResults(cards)

    private val _cards = mutableListOf<Card>()
    val cards
        get() = _cards.toList()

    init {
        initialCards.forEach {
            add(it)
        }
    }

    fun add(card: Card) {
        _cards.add(card)
    }

    fun addAll(servedCards: Cards) {
        _cards.addAll(servedCards.cards)
    }

    private fun getAllPossibleResults(cards: List<Card>): List<Int> {
        val results = mutableListOf<Int>()
        calculateRecursive(cards, results)

        return results
    }

    private fun calculateRecursive(cards: List<Card>, results: MutableList<Int>, idx: Int = 0, sum: Int = 0) {
        if (idx == cards.size) {
            results.add(sum)
            return
        }

        val card = cards[idx]
        if (card.cardNumber.isMultiple) {
            card.cardNumber.multiNumbers?.forEach {
                calculateRecursive(cards, results, idx + 1, sum + it)
            }
        } else {
            calculateRecursive(cards, results, idx + 1, sum + card.cardNumber.number)
        }
    }
}
