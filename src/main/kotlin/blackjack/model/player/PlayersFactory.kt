package blackjack.model.player

import blackjack.model.trump.Cards

object PlayersFactory {
    fun create(playerNames: List<String>): Players {
        return Players.Builder().players(playerNames.map { Player(Cards.firstDraw(), it) }).build()
    }
}
