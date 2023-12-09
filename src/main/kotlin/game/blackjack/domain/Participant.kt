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
}
