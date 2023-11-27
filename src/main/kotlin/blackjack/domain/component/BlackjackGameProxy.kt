package blackjack.domain.component

import blackjack.domain.model.PlayerName

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

    private fun BlackjackGame?.checkGameExist(): BlackjackGame {
        check(this != null) { "게임이 존재하지 않습니다." }

        return this
    }
}
