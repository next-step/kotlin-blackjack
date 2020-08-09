package blackjack.model

class Player(val name: String) {

    private val _myReceivedDeck = mutableListOf<Deck>()
    val myReceivedDeck: List<Deck> get() = _myReceivedDeck

    fun requestDeck(deck: Deck) {
        _myReceivedDeck.add(deck)
    }
}
