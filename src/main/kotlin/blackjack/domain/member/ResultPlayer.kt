package blackjack.domain.member

import blackjack.domain.GameState

class ResultPlayer(
    val player: Player,
    private val gameState: GameState
) {

    fun benefit(): Double {
        return gameState.benefit(player.bet)
    }
}
