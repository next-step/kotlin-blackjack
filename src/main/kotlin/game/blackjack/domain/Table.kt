package game.blackjack.domain

import game.blackjack.view.ResultView

class Table(
    private val players: List<Player>,
    private val resultView: ResultView,
    private val getAction: (name: String) -> String,
    private val showPlayerCard: (player: Player) -> Unit,
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
                it.determine(getAction(it.name))
                if (it.canReceive()) {
                    it.receive(dealer.drawCard())
                }
                showPlayerCard(it)
            }
        }
    }

    companion object {
        private const val INIT_CARD_COUNT = 2
    }
}
