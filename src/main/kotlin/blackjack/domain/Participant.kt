package blackjack.domain

sealed class Participant(val name: String) {
    private val _cards: MutableList<Card> = mutableListOf()

    val cards: List<Card>
        get() = _cards.toList()

    fun receive(vararg cards: Card) {
        _cards.addAll(cards)
    }

    fun receive(card: Card) {
        _cards.add(card)
    }

    fun calculateTotalScore(): Int {
        return ScoreCalculator.calculate(cards)
    }

    abstract fun canNotReceiveCard(): Boolean
}
