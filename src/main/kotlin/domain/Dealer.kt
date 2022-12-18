package domain

class Dealer(
    val deck: CardDeck = CardDeck()
) {
    init {
        deck.shuffle()
    }

    fun giveCard(player: Player) {
        val card = deck.getTopCard()
        player.receiveCard(card = card)
    }
}
