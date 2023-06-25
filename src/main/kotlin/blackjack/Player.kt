package blackjack

open class Player(
    val name: String = "",
    private val _cards: MutableList<Card> = mutableListOf(),
    round: Round = Round()
) {
    val cards: List<Card> get() = _cards.toList()

    val sum: Int get() = PointCalculator.sum(cards)

    init {
        if (_cards.isEmpty()) {
            _cards.add(round.getCard())
            _cards.add(round.getCard())
        }
    }

    open fun canGetCard(): Boolean {
        return sum < Rule.BLACK_JACK
    }

    fun addCard(card: Card) {
        _cards.add(card)
    }
}
