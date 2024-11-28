package blackjack.domain.card

class Cards(private val cards: MutableList<Card> = mutableListOf()) {
    fun add(card: Card) {
        cards.add(card)
    }

    fun getCards(): List<Card> = cards

    fun sum(): Int {
        var totalSum = cards.sumOf { it.number.value }
        var aceCount = cards.filter { it.number == CardNumber.Ace }.size

        while (--aceCount >= 0 && isGreaterThanMaxSum(totalSum + CardNumber.Ace.toEleven())) {
            totalSum += CardNumber.Ace.toEleven()
        }

        return totalSum
    }

    private fun isGreaterThanMaxSum(value: Int) = value <= MAX_CARDS_NUM_SUM

    companion object {
        const val MAX_CARDS_NUM_SUM = 21
    }
}
