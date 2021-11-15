package blackjack.domain

class PlayerCards : ParticipantCards {
    private val _cards: MutableList<Card> = mutableListOf()

    override val cards: List<Card>
        get() = _cards

    override fun toString(): String {
        return _cards.joinToString { it.toString() }
    }

    override fun add(card: Card) {
        _cards.add(card)
    }

    companion object {
        const val MAXIMUM_SUM_OF_CARD_NUMBERS = 21
    }
}
