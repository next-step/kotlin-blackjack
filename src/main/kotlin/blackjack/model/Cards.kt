package blackjack.model

class Cards(cards: Set<Card> = setOf()) {
    private val _cards = cards.toMutableSet()
    val cards: Set<Card>
        get() = _cards.toSet()

    fun addNewCard() {
        _cards.add(Card.takeRandomCard())
    }

    fun calculateCardValues(): Int {
        var total = 0
        var aceCount = 0

        // 모든 카드의 값을 합산하고, 에이스의 개수를 셈
        for (card in _cards) {
            total += card.getValue()
            if (card.isAce) {
                aceCount++
            }
        }

        // 에이스의 값을 11로 변경할 수 있는지 확인하고, 가능하면 변경
        while (aceCount > 0 && total + 10 <= 21) {
            total += 10
            aceCount--
        }

        return total
    }

    override fun toString(): String {
        return _cards.toString()
    }
}
