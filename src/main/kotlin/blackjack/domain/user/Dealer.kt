package blackjack.domain.user

import blackjack.domain.card.Deck

class Dealer(private val deck: Deck = Deck.create(), name: String = "dealer") : Player(name) {
    fun giveCardTo(player: Player, cardCount: Int = 1) {
        repeat(cardCount) { player.addCard(deck.getNextCard()) }
        player.updateStatus()
    }

    fun giveCardIfPlayerWantHit(player: Player) {
        if (player.wantHit()) {
            giveCardTo(player)
        }
    }
}
