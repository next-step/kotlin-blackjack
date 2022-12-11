package blackjack.domain

class Cards(
    private val _value: MutableList<Card> = mutableListOf(),
) {
    val value: List<Card>
        get() = _value.toList()

    companion object {
        val CARD_DECK = listOf<Card>()
    }
}
