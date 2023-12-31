package blackjack.mock

import blackjack.controller.InputProcessor
import blackjack.domain.Action
import blackjack.domain.batting.BetAmount
import blackjack.domain.player.Player
import blackjack.domain.player.PlayerNames

class InputProcessorMock(
    private val playerNames: List<String> = listOf("kim", "lee"),
    private val action: Action = Action.HIT,
    private val betAmount: BetAmount = BetAmount(amount(3_000))
) : InputProcessor {

    override fun playerNames(): PlayerNames = PlayerNames.from(playerNames)
    override fun playerBet(player: Player): BetAmount = betAmount

    override fun playerAction(player: Player): Action = action
}
