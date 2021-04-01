package blackjack

class Cards {
    private val _cards = mutableListOf<Card>()
    val cards: List<Card>
        get() = _cards.toList()

    fun add(card: Card) {
        _cards.add(card)
    }

    fun getScore(): Int {
        return cards.sortedBy { it.cardNumber.score }
            .fold(0) { acc, card -> card.cardNumber.addExecute(acc) }
    }
}
