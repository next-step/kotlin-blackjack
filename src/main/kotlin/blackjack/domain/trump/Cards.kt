package blackjack.domain.trump

const val MAX_SUM_OF_CARDS: Int = 21

class Cards(val values: List<Card>) {

    fun score(): Int {
        val sum = sumOf()
        return if (isAbleToConvertAce(sum)) sum + ACE_THRESHOLDS else sum
    }

    fun add(card: Card): Cards {
        return Cards(values + card)
    }

    private fun sumOf(): Int {
        return values.sumOf { it.number.value }
    }

    private fun isContainsAce(): Boolean {
        return values.any { it.isAce() }
    }

    private fun isAbleToConvertAce(sum: Int): Boolean {
        return isContainsAce() && sum + ACE_THRESHOLDS <= MAX_SUM_OF_CARDS
    }

    companion object {
        private const val ACE_THRESHOLDS: Int = 10
    }
}
