package model

class Dealer(
    name: String = "딜러",
    private val deck: Deck = Deck()
) : Player(name) {
    fun pick(): Card = deck.pick()
    fun cardCount(): Int = deck.count()
}
