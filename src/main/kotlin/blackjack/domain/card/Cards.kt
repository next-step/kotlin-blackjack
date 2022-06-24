package blackjack.domain.card

@JvmInline
value class Cards(
    private val _value: MutableList<Card> = mutableListOf(),
) {
    val value
        get() = _value.toList()
    val size: Int
        get() = _value.size

    fun addAll(cards: List<Card>) {
        _value.addAll(cards)
    }
}
