package blackjack.domain

class Dealer(
    val name: String = "딜러",
    val cards: Cards,
    val deck: Deck
) {

    fun draw(count: Int): Cards {
        return deck.drawCard(count)
    }
}
