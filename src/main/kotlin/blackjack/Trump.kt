package blackjack

class Trump(private val _cards: MutableList<Card> = defaultTrump()) {
    val cards: List<Card> get() = _cards.toList()

    fun getCard(): Card = _cards.removeLastOrNull() ?: throw IllegalStateException("더 이상 나누어줄 카드가 없습니다")

    companion object {
        fun defaultTrump() =
            CardType.values().flatMap { CardValue.values().map { value -> Card.from(it, value) } }.toMutableList()
    }
}
