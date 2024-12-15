package blackjack.domain

import blackjack.entity.Game
import blackjack.entity.Player
import blackjack.repository.GameRepository

object BlackJackGame {
    const val INIT_FACE_UP = 2
    const val DEFAULT_FACE_UP = 1

    lateinit var gameRepository: GameRepository

    fun setGameRepository(game: Game) {
        gameRepository = GameRepository(game)
    }

    fun getGameInfo(): Game {
        return gameRepository.findAll()
    }

    fun gameContinue(playerName: String): Player {
        val player = gameRepository.findPlayerByName(playerName)
        player.hand.addCards(Deal.giveCards(DEFAULT_FACE_UP))
        return player
    }

    fun gameContinueDealer() {
        val dealer = gameRepository.findDealer()
        dealer.hand.addCards(Deal.giveCards(DEFAULT_FACE_UP))
    }

    fun getGameResult(): BlackJackResult {
        val game = gameRepository.findAll()
        return game.calculateResult()
    }
}
