package blackjack.domain

class Cards(
    private val _value: MutableList<Card> = mutableListOf(),
) {
    val value: List<Card>
        get() = _value.toList()

    val size: Int
        get() = _value.size

    fun shuffle() = _value.shuffle()

    fun add(card: Card) {
        TODO("Not Implementation")
    }
}
