package blackjack.controller

import blackjack.domain.Action
import blackjack.domain.batting.BetAmount
import blackjack.domain.player.Player
import blackjack.domain.player.PlayerNames
import blackjack.view.dto.PlayerNameDto
import blackjack.view.input.InputView

class ViewInputProcessor : InputProcessor {
    override fun playerNames(): PlayerNames =
        PlayerNames.from(InputView.playerNames())

    override fun playerBet(player: Player): BetAmount =
        BetAmount(InputView.playerBet(player.nameDto()).toBigDecimal())

    override fun playerAction(player: Player): Action {
        val action = InputView.playerAction(player.nameDto())
        return toPlayerAction(action)
    }

    private fun Player.nameDto(): PlayerNameDto = PlayerNameDto.from(this)

    private fun toPlayerAction(action: String): Action = when (action) {
        "y" -> Action.HIT
        "n" -> Action.STAND
        else -> throw IllegalArgumentException("플레이어 액션에 대한 입력 값은 y 또는 n 이어야 합니다")
    }
}
