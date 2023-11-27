package blackjack

import blackjack.contoller.BlackjackController
import blackjack.domain.component.BlackjackGameProxy
import blackjack.domain.component.BlackjackInputValidator
import blackjack.domain.model.PlayerName
import blackjack.view.BlackjackInputView
import blackjack.view.BlackjackResultView

fun main() {
    val controller = init()

    process(controller)
}

private fun init(): BlackjackController {
    return BlackjackController(
        BlackjackInputView(),
        BlackjackInputValidator(),
        BlackjackResultView(),
        BlackjackGameProxy()
    )
}

private fun process(blackjackController: BlackjackController) {
    val playerNames: List<PlayerName> = blackjackController.getPlayerNames()

    blackjackController.initGame(playerNames)
    blackjackController.printInitialGameStatus()

    playerNames.forEach {
        it.play(blackjackController)
    }

    blackjackController.printPlayerInfos()
}

private fun PlayerName.play(blackjackController: BlackjackController) {
    while (blackjackController.getHitPossible(this)) {
        if (blackjackController.getHit(this).isNo()) {
            return
        }

        val player = blackjackController.hit(this)

        blackjackController.printPlayerInfo(player)
    }
}
