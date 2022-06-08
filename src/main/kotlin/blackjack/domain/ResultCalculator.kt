package blackjack.domain

class ResultCalculator {

    fun getAllPossibleResults(cards: List<Card>): List<Int> {
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
