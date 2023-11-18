package blackjack.mock

import blackjack.controller.InputProcessor
import blackjack.domain.PlayerAction
import blackjack.domain.player.Player
import blackjack.domain.player.PlayerNames

class InputProcessorMock(
    private val playerNames: List<String>? = null,
) : InputProcessor {

    override fun playerNames(): PlayerNames {
        val playNamesInput = playerNames ?: listOf("kim", "lee")
        return playNamesInput.let(PlayerNames::of)
    }

    override fun playerAction(player: Player): PlayerAction {
        TODO("Not yet implemented")
    }
}
