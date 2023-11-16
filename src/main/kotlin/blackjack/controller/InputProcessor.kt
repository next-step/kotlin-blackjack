package blackjack.controller

import blackjack.domain.player.PlayerNames
import blackjack.view.input.InputView

object InputProcessor {
    val playerNames: PlayerNames
        get() = InputView.playerNames().let(PlayerNames::of)
}
