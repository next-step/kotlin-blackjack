package blackjack.mock

import blackjack.controller.InputProcessor
import blackjack.domain.PlayerAction
import blackjack.domain.player.Player
import blackjack.domain.player.PlayerNames

class InputProcessorMock(
    private val playerNames: List<String> = listOf("kim", "lee"),
    private val playerAction: PlayerAction = PlayerAction.HIT,
) : InputProcessor {

    override fun playerNames(): PlayerNames = playerNames.let(PlayerNames::of)

    override fun playerAction(player: Player): PlayerAction = playerAction
}
