package blackjack.domain

import blackjack.view.InputView

class BlackJackGame(
    val inputView: InputView,
    val cardManager: CardManager,
    val players: List<Player>
) {
    init {
        require(players.size >= MINIMUM_PLAYERS) { "플레이어는 최소 2명 이상 필요합니다." }
        repeat(INITIAL_CARD_SIZE) {
            setUp()
        }
        // inputView.printSetUp(players)
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
