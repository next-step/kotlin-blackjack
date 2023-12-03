package blackjack.mock

import blackjack.controller.InputProcessor
import blackjack.domain.Action
import blackjack.domain.batting.Amount
import blackjack.domain.player.Player
import blackjack.domain.player.PlayerNames

class InputProcessorMock(
    private val playerNames: List<String> = listOf("kim", "lee"),
    private val action: Action = Action.HIT,
    private val betAmount: Amount = amount(3_000)
) : InputProcessor {

    override fun playerNames(): PlayerNames = playerNames.let(PlayerNames::from)
    override fun playerBet(player: Player): Amount = betAmount

    override fun playerAction(player: Player): Action = action
}
