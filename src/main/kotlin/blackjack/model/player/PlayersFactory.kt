package blackjack.model.player

import blackjack.model.trump.Cards

object PlayersFactory {
    fun create(playerNames: List<String>): Players {
        val players = playerNames.map { Player(Cards.firstDraw(), it) }

        return Players.Builder().players(players).build()
    }
}
