package blackjack.domain

class Player(val name: String) {
    private val _cards = mutableSetOf<Card>()

    val cards: Set<Card>
        get() = _cards.toSet()

    fun receiveCard(card: Card) {
        _cards.add(card)
    }

    fun isBust() = sumOfPoints() > MAX_POINT

    fun sumOfPoints(): Int =
        if (_cards.any { it.number.anotherValue != null }) {
            var result = _cards.filterNot { it.number.anotherValue != null }
                .sumOf { it.number.value }

            _cards.filter { it.number.anotherValue != null }
                .forEach {
                    // filter에서 이미 체크함
                    val anotherValue = it.number.anotherValue!!
                    result += if (result + anotherValue > MAX_POINT) {
                        it.number.value
                    } else {
                        it.number.anotherValue
                    }
                }
            result
        } else {
            _cards.sumOf { it.number.value }
        }

    companion object {
        const val MAX_POINT = 21
    }
}
