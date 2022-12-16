package blackjack.domain

import blackjack.view.InputView
import blackjack.view.OutputView

class BlackJackGame(
    val inputView: InputView,
    val outputView: OutputView,
    val cardManager: CardManager,
    val players: List<Player>
) {
    init {
        require(players.size >= MINIMUM_PLAYERS) { "플레이어는 최소 2명 이상 필요합니다." }
        repeat(INITIAL_CARD_SIZE) {
            setUp()
        }
        outputView.printSetUp(players)
    }

    private fun setUp() {
        players.forEach {
            it.give(cardManager.getCard())
        }
    }

    fun play() {
        TODO()
    }

    companion object {
        private const val INITIAL_CARD_SIZE = 2
        private const val MINIMUM_PLAYERS = 2
    }
}
