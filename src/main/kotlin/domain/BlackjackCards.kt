package domain

class BlackjackCards() {
    private val _cards: MutableList<Card> = mutableListOf()

    val cards: List<Card>
        get() = _cards.toList()

    companion object {
        const val BLACKJACK_MAX_SCORE = 21
    }

    fun receiveCard(card: Card) {
        _cards.add(card)
    }

    fun calculateScore(): Int {
        var total = _cards.sumOf { it.value.basicScore }
        var aceCount = _cards.count { it.value == CardValue.ACE }

        while (total > BLACKJACK_MAX_SCORE && aceCount > 0) {
            total -= 10 // ACE 11 → 1 변경
            aceCount--
        }

        return total
    }

    fun displayCardInfo(): String {
        return _cards.joinToString()
    }
}
