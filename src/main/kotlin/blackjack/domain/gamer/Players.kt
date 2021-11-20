package blackjack.domain.gamer

import blackjack.domain.deck.Deck

class Players private constructor(
    value: List<Player>,
) {
    val value = value.toList()

    fun settingTable(deck: Deck): List<Player> {
        val completedDealPlayers = mutableListOf<Player>()
        for (player in value) {
            val playerCompletedDeal = player.completeDeal(deck)
            completedDealPlayers.add(playerCompletedDeal)
        }
        return completedDealPlayers
    }

    companion object {
        fun from(players: List<Player>): Players {
            return Players(players)
        }
    }
}
