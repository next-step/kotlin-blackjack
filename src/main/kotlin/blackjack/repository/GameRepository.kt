package blackjack.repository

import blackjack.entity.Game

class GameRepository {
    private var players: List<Game> = emptyList()

    fun findAll(): List<Game> {
        return players.toList()
    }

    fun findByName(player: String): Game? {
        return players.find { it.player == player }
    }

    fun savePlayers(players: List<Game>) {
        this.players = players

    }
}
