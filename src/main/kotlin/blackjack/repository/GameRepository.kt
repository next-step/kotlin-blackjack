package blackjack.repository

import blackjack.entity.Dealer
import blackjack.entity.Game
import blackjack.entity.Player

class GameRepository(private val game: Game) {
    fun findAll(): Game {
        return this.game
    }

    fun findPlayerByName(name: String): Player {
        return this.game.players.first { it.name == name }
    }

    fun findDealer(): Dealer {
        return this.game.dealer
    }
}
