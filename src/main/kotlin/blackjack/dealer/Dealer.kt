package blackjack.dealer

import blackjack.player.Player

class Dealer {

    fun dealInitialCards(players: List<Player>) {
        players.forEach { player ->
            drawCard(player)
            drawCard(player)
        }
    }
    fun drawCard(player: Player) {
        player.addCard(CardDeck.draw())
    }
}
