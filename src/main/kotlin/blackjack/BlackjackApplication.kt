package blackjack

import blackjack.contoller.BlackjackController
import blackjack.domain.component.BlackjackInputValidator
import blackjack.domain.model.PlayerName
import blackjack.view.BlackjackInputView

fun main() {
    val blackjackController = BlackjackController(
        BlackjackInputView(),
        BlackjackInputValidator()
    )

    val playerNames: List<PlayerName> = blackjackController.getPlayerNames()
}
