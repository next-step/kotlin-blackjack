package blackjack.domain.deck

class Cards(
    private val _value: MutableList<Card> = mutableListOf(),
) {
    val value = _value

    fun receiveCard(card: Card) {
        _value.add(card)
    }

    fun haveCards(): String = _value.joinToString { it.toString() }

    fun calculateTotalScore(): Int = _value.sumOf { it.getScore() }
}
