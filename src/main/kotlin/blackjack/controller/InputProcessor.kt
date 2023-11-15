package blackjack.controller

import blackjack.domain.player.PlayerNames
import blackjack.view.InputView

object InputProcessor {
    val playerNames: PlayerNames
        get() = InputView.playerNames().let(PlayerNames::of)
}
