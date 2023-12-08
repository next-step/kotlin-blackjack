package game.blackjack.domain

class Participant(val name: String) {
    private val _cards: MutableList<Card> = mutableListOf()
    val cards: List<Card> get() = _cards.toList()

    fun drawCard(card: Card) {
        _cards.add(card)
    }

    fun drawCard(cards: List<Card>) {
        _cards.addAll(cards)
    }
}
