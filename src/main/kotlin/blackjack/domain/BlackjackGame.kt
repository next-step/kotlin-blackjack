package blackjack.domain

import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackGame(
    private val dealer: Dealer,
    val players: List<Player>
) {
    init {
        initGame()
    }

    private fun initGame() {
        repeat(INIT_DEAL_COUNT) { initDeal() }
    }

    private fun initDeal() {
        players.forEach { player -> dealer.deal(player) }
    }

    fun isNotFinished(): Boolean = players.any { it.state == PlayerState.PLAYING }

    fun start() {
        while (isNotFinished()) {
            players.forEach { player ->
                if (player.state == PlayerState.PLAYING) {
                    val inputDrawResponse = InputView.inputDrawResponse(player)
                    if (inputDrawResponse) {
                        dealer.deal(player)
                        OutputView.printCardsInHand(player)
                    }
                    else player.stopDraw()
                }
            }
        }
    }

    companion object {
        private const val INIT_DEAL_COUNT = 2
    }
}
