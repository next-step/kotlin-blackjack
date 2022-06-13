package blackjack.domain

class Cards {
    private val _cards = mutableListOf<Card>()
    val cards: List<Card>
        get() = _cards

    fun start(list: List<Card>): Cards {
        _cards.addAll(list)
        return this
    }

    fun add(card: Card) = _cards.add(card)

    fun underTheBlackJack(newCard: Card): Boolean {
        return _cards.sumOf { it.value.number } + newCard.value.number <= BLACKJACK_NUMBER
    }

    fun size() = _cards.size

    companion object {
        private const val BLACKJACK_NUMBER = 21
    }
}
