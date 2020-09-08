package blackJack.domain

class Hands {
    private val _cards: MutableList<Card> = mutableListOf()
    val cards
        get() = _cards.toList()

    fun add(card: Card) {
        _cards.add(card)
    }

    fun getTotalScore(): Int = TotalScore.get(this)

    fun getCardsScore(): Int = _cards.sumBy { it.number }

    fun hasAce(): Boolean = _cards.any { it.number == ACE }

    fun isBlackJack(): Boolean = _cards.size == BLACK_JACK_HANDS && getTotalScore() == BLACK_JACK_SCORE

    fun getFirstCard(): Card = _cards[0]

    companion object {
        private const val ACE = 1
        private const val BLACK_JACK_HANDS = 2
        private const val BLACK_JACK_SCORE = 21
    }
}
