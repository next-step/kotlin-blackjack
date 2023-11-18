package blackjack.controller

import blackjack.domain.PlayerAction
import blackjack.domain.player.Player
import blackjack.domain.player.PlayerNames
import blackjack.view.input.InputView
import blackjack.view.model.PlayerNameModel

object InputProcessor {
    val playerNames: PlayerNames
        get() = InputView.playerNames().let(PlayerNames::of)

    fun playerAction(player: Player): PlayerAction {
        val action = InputView.playerAction(player.let(PlayerNameModel::from))
        return toPlayerAction(action)
    }

    private fun toPlayerAction(action: String): PlayerAction = when (action) {
        "y" -> PlayerAction.HIT
        "n" -> PlayerAction.STAND
        else -> throw IllegalArgumentException("플레이어 액션에 대한 입력 값은 y 또는 n 이어야 합니다")
    }
}
