package blackjack

abstract class Gamer(private val name: String) {

    private val _myCards = mutableListOf<Card>()
    val myCards: List<Card> get() = _myCards

    override fun toString(): String = name

    fun requestCard(card: Card) {
        _myCards.add(card)
    }
}
