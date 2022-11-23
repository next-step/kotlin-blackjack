package blackjack

data class CardDeck(
    val deck: MutableList<Card> = Card.values().toMutableList()
){
    init {
        deck.shuffled()
    }

    fun deal(): Card {
        return deck.removeFirst()
    }
}
