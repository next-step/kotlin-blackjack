package camp.nextstep.blackjack

class Player(val name: String) {
    private val _cards = mutableListOf<Card>()
    val cards get() = _cards.toList()

    fun receive(card: Card) {
        _cards.add(card)
    }
}
