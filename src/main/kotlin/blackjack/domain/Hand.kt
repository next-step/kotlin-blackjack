package blackjack.domain

data class Hand(
    val cards: MutableList<Card> = mutableListOf()
) {
    fun init(card1: Card, card2: Card) {
        cards.add(card1)
        cards.add(card2)
    }

    fun receive(card: Card) {
        cards.add(card)
    }

    fun canHit(): Boolean {
        return getSum() < BLACKJACK_NUMBER
    }

    fun getSum(): Int {
        val sum = cards.sumOf {
            it.num.value
        }
        if (containsAce() && sum <= ACE_NUMBER) {
            return sum + ACE_ADDING_NUMBER
        }
        return sum
    }

    private fun containsAce(): Boolean {
        return cards.any {
            it.num == CardNumber.ACE
        }
    }

    companion object {
        private const val BLACKJACK_NUMBER = 21
        private const val ACE_NUMBER = 11
        private const val ACE_ADDING_NUMBER = 10
    }
}
