package blackjack

class Player(val name: String) {
    private val _cards = mutableListOf<Card>()
    val cards
        get() = _cards.toList()

    fun offer(card: Card) {
        _cards.add(card)
    }

    fun offer(servedCards: List<Card>) {
        _cards.addAll(servedCards)
    }

    fun getCardSum(): Int {
        return ResultCalculator().calculateRecursive(cards)
    }
}
