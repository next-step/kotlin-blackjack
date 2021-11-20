package blackjack.domain.gamer

import blackjack.domain.deck.Deck

class Players private constructor(
    value: List<Player>,
) {
    val value = value.toList()

    fun prepare(deck: Deck): Players {
        val preparedPlayers = mutableListOf<Player>()
        for (player in value) {
            val preparedPlayer = player.prepare(deck)
            preparedPlayers.add(preparedPlayer)
        }
        return Players(preparedPlayers)
    }

    companion object {
        fun from(players: List<Player>): Players {
            return Players(players)
        }
    }
}
