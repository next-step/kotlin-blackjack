package blackjack.domain

sealed class Participant(val name: String) {
    private val _cards: Cards = Cards()

    val cards: List<Card>
        get() = _cards.elements

    fun receive(vararg cards: Card) {
        _cards.addAll(*cards)
    }

    fun receive(card: Card) {
        _cards.add(card)
    }

    fun calculateTotalScore(): Int {
        return _cards.calculate()
    }

    abstract fun canNotReceiveCard(): Boolean
}
