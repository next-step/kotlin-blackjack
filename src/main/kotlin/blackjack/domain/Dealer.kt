package blackjack.domain

class Dealer {
    val cardDeck: CardDeck = CardDeck()

    fun draw(): Card {
        return cardDeck.draw()
    }

    fun deal(player: Player) {
        player.addCard(draw())
    }
}
