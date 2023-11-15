package blackjack.controller

import blackjack.domain.PlayerNames
import blackjack.view.InputView

object InputProcessor {
    val playerNames: PlayerNames
        get() = InputView.playerNames().let(PlayerNames::of)
}
