package blackjack.domain

class Dealer(
    val deck: Deck
) {

    fun draw(count: Int): Cards {
        return deck.drawCard(count)
    }
}
