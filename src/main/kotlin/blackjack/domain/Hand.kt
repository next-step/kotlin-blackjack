package blackjack.domain

class Hand(
    private val _cards: MutableList<Card> = mutableListOf()
) {
    val cards: List<Card>
        get() = _cards

    fun init(card1: Card, card2: Card) {
        _cards.add(card1)
        _cards.add(card2)
    }

    fun receive(card: Card) {
        _cards.add(card)
    }

    fun canHit(): Boolean {
        return getSum() < BLACKJACK
    }

    fun getSum(): Int {
        val sum = _cards.sumOf {
            it.num.value
        }
        if (containsAce() && sum <= ACE_NUMBER) {
            return sum + ACE_ADDING_NUMBER
        }
        return sum
    }

    private fun containsAce(): Boolean {
        return _cards.any {
            it.num == CardNumber.ACE
        }
    }

    companion object {
        private const val BLACKJACK = 21
        private const val ACE_NUMBER = 11
        private const val ACE_ADDING_NUMBER = 10
    }
}
