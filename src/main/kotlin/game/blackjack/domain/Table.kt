package game.blackjack.domain

import game.blackjack.view.InputView
import game.blackjack.view.ResultView

class Table(
    private val players: List<Player>,
    private val inputView: InputView,
    private val resultView: ResultView,
) {

    private val dealer = Dealer()

    fun start() {
        init()
        resultView.printAllPlayerCard(players)
        distribute()
        resultView.printResult(players)
    }

    private fun init() {
        players.forEach {
            repeat(INIT_CARD_COUNT) { _ -> it.receive(dealer.drawCard()) }
        }
    }

    fun distribute() {
        players.forEach {
            while (it.canReceive()) {
                it.determine(inputView.readPlayerAction(it.name))
                if (it.canReceive()) {
                    it.receive(dealer.drawCard())
                }
                resultView.printPlayerCard(it)
            }
        }
    }

    companion object {
        private const val INIT_CARD_COUNT = 2
    }
}
