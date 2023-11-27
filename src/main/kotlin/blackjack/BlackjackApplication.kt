package blackjack

import blackjack.contoller.BlackjackController
import blackjack.domain.component.BlackjackGameProxy
import blackjack.domain.component.BlackjackInputValidator
import blackjack.domain.model.PlayerName
import blackjack.view.BlackjackInputView
import blackjack.view.BlackjackResultView

fun main() {
    val blackjackController = BlackjackController(
        BlackjackInputView(),
        BlackjackInputValidator(),
        BlackjackResultView(),
        BlackjackGameProxy()
    )

    val playerNames: List<PlayerName> = blackjackController.getPlayerNames()
    blackjackController.initGame(playerNames)
    blackjackController.printInitialGameStatus()

    playerNames.forEach {
        if (!blackjackController.getHitPossible(it)) {
            return@forEach
        }

        if (blackjackController.getHit(it).isNo()) {
            return@forEach
        }

        val player = blackjackController.hit(it)

        blackjackController.printPlayerInfo(player)
    }

    blackjackController.printPlayerInfos()
}
