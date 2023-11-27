package blackjack.domain.component

import blackjack.domain.model.Player
import blackjack.domain.model.PlayerName
import blackjack.domain.model.Players

class BlackjackGameProxy(
    private var blackjackGame: BlackjackGame? = null
) {
    fun init(playerNames: List<PlayerName>) {
        blackjackGame = BlackjackGame.create(playerNames)
    }

    fun isHitPossible(playerName: PlayerName): Boolean {
        return blackjackGame
            .checkGameExist()
            .isHitPossible(playerName)
    }

    fun hit(playerName: PlayerName): Player {
        return blackjackGame
            .checkGameExist()
            .hit(playerName)
    }

    fun getPlayers(): Players {
        return blackjackGame
            .checkGameExist()
            .players
    }

    private fun BlackjackGame?.checkGameExist(): BlackjackGame {
        check(this != null) { "게임이 존재하지 않습니다." }

        return this
    }
}
