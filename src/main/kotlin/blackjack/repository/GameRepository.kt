package blackjack.repository

import blackjack.entity.Game

class GameRepository {
    private val players: MutableList<Game> = mutableListOf()

    fun savePlayer(game: Game) {
        players.add(game)
    }

    fun findAll(): MutableList<Game> {
        return players.toMutableList()
    }

    fun findByName(player: String): Game? {
        return players.find { it.player == player }
    }
}
