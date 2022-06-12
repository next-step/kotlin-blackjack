package blackjack.domain

import blackjack.domain.interfaces.RandomCardFactory
import blackjack.view.Screen

class Game(val dealer: Dealer) {
    fun enter(input: String): List<Player> {
        val players = mutableListOf<Player>()
        for (name in input.split(",")) {
            players.add(Player(name))
        }

        return players
    }

    fun shareCards(players: List<Player>) {
        for (player in players) {
            shareCard(player)
        }
    }

    private fun shareCard(player: Player) {
        dealer.shareCards().map { card -> player.takeCard(card) }
    }

    fun shareMoreCards(player: Player) {
        var needCard = dealer.ask(player, readln())
        if (!needCard) {
            return Screen.displayPlayerCard(player)
        }

        while (needCard) {
            player.takeCard(dealer.give(RandomCardFactory()))
            Screen.displayPlayerCard(player)
            needCard = dealer.ask(player, readln())
        }
    }
}
