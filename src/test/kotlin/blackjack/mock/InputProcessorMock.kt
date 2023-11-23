package blackjack.mock

import blackjack.controller.InputProcessor
import blackjack.domain.Action
import blackjack.domain.player.Player
import blackjack.domain.player.PlayerNames

class InputProcessorMock(
    private val playerNames: List<String> = listOf("kim", "lee"),
    private val action: Action = Action.HIT,
) : InputProcessor {

    override fun playerNames(): PlayerNames = playerNames.let(PlayerNames::from)

    override fun playerAction(player: Player): Action = action
}
