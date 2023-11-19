package blackjack.domain

class Hand(
    private val _cards: MutableList<Card> = mutableListOf()
) {
    val cards: List<Card>
        get() = _cards

    fun init(cards: List<Card>) {
        require(cards.size == 2) { "처음엔 카드 2장만 받을 수 있습니다." }
        _cards.addAll(cards)
    }

    fun receive(card: Card) {
        _cards.add(card)
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
        private const val ACE_NUMBER = 11
        private const val ACE_ADDING_NUMBER = 10
    }
}
