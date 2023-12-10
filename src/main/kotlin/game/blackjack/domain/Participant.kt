package game.blackjack.domain

class Participant(val name: String) {
    private val _handCards: MutableList<Card> = mutableListOf()
    val handCards: List<Card> get() = _handCards.toList()

    fun drawCard(card: Card) {
        _handCards.add(card)
    }

    fun drawCard(cards: List<Card>) {
        _handCards.addAll(cards)
    }

    fun isBust(): Boolean {
        return _handCards.sumOf { it.number.number } > Blackjack.WINNING_SCORE
    }

    fun isNotBust(): Boolean {
        return !isBust()
    }

    override fun toString(): String {
        return "${name}카드: ${_handCards.map { it.toString() }.joinToString(", ")}"
    }
}
