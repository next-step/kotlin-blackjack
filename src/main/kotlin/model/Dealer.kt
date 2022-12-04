package model

class Dealer(
    private val trump: Deck = Deck()
) {
    fun pick(): Card = trump.pick()
    fun cardCount(): Int = trump.count()
}
