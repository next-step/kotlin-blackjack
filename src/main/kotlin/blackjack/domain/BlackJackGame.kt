package blackjack.domain

import blackjack.view.InputView
import blackjack.view.OutputView

class BlackJackGame(
    private val inputView: InputView,
    private val outputView: OutputView,
    private val cardManager: CardManager,
    private val players: List<Player>
) {
    private var stopCountDown: Int

    init {
        require(players.size >= MINIMUM_PLAYERS) { "플레이어는 최소 2명 이상 필요합니다." }
        repeat(INITIAL_CARD_SIZE) {
            setUp()
        }
        outputView.printSetUp(players)
        stopCountDown = players.size
    }

    private fun setUp() {
        players.forEach {
            it.give(cardManager.getCard())
        }
    }

    fun play() {
        while (stopCountDown > 0) {
            players.forEach {
                playEachRound(it)
            }
        }
    }

    private fun playEachRound(player: Player) {
        if (player.stop || cardManager.isCardEmpty()) {
            return
        }
        val answer = inputView.inputMoreCardAnswer(player)
        if (answer == MoreCardAnswer.NO) {
            stopCountDown--
            player.stopGame()
            return
        }
        player.give(cardManager.getCard())
        outputView.printEachPlayer(player)
    }

    companion object {
        private const val INITIAL_CARD_SIZE = 2
        private const val MINIMUM_PLAYERS = 2
    }
}
