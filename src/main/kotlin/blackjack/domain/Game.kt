package blackjack.domain

import blackjack.domain.interfaces.RandomCardFactory

class Game() {
    fun enter(input: String): List<Player> {
        val players = mutableListOf<Player>()
        for (name in input.split(",")) {
            players.add(Player(name))
        }

        return players
    }

    fun start(players: List<Player>, dealer: Dealer) {
        takeCards(players, dealer)
    }

    private fun takeCards(players: List<Player>, dealer: Dealer) {
        for (player in players) {
            takeCard(player, dealer)
        }
    }

    private fun takeCard(player: Player, dealer: Dealer) {
        repeat(Dealer.BASIC_CARD_AMOUNT) {
            player.takeCard(dealer.give(RandomCardFactory()))
        }
    }
}
