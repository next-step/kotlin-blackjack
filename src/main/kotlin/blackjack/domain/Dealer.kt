package blackjack.domain

class Dealer(
    val deck: Deck
) {

    fun drawCardsFromDeck(count: Int): Cards {
        return deck.drawCard(count)
    }
}
