package blackjack

import blackjack.contoller.BlackjackController
import blackjack.domain.component.BlackjackGameProxy
import blackjack.domain.component.BlackjackInputValidator
import blackjack.domain.model.PlayerName
import blackjack.view.BlackjackInputView

fun main() {
    val blackjackController = BlackjackController(
        BlackjackInputView(),
        BlackjackInputValidator(),
        BlackjackGameProxy()
    )

    val playerNames: List<PlayerName> = blackjackController.getPlayerNames()
    blackjackController.initGame(playerNames)
    playerNames.forEach {
        if (!blackjackController.getHitPossible(it)) {
            return@forEach
        }
    }
}
