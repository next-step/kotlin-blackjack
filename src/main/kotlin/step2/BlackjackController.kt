package step2

import step2.domain.Players
import step2.ui.InputView
import step2.ui.OutputView

class BlackjackController {

    fun execute() {
        val players = start()
    }

    private fun start(): Players {
        val players = InputView.readPlayer()

        players.players.forEach {
            it.draw(INITIAL_DRAW_CARD_COUNT)
        }

        OutputView.printDrawInitialCards(players)

        return players
    }

 companion object {
     private const val INITIAL_DRAW_CARD_COUNT = 2
 }
}
