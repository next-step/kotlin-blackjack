package blackjack.dealer

import blackjack.player.Player

class Dealer {
    fun drawCards(player: Player) {
        player.addCard(CardDeck.draw())
        player.addCard(CardDeck.draw())
    }
}
