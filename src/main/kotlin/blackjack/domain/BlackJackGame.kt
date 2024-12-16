package blackjack.domain

import blackjack.view.InputView
import blackjack.view.OutputView

class BlackJackGame(
    private val players: Players,
    private val gameCards: GameCards,
) {
    fun startGame() {
        repeat(FIRST_ROUND_HAND_SIZE) {
            giveOutCards()
        }
    }

    private fun giveOutCards() {
        players.forEach { players ->
            players.receiveCard(gameCards.drawCard())
        }
    }

    fun handlePlayerDraw(
        player: Player,
        gameCards: GameCards,
    ) {
        val previousCardCount = player.cardSize()
        InputView.enterIsContinueDrawCard(player)
        if (player.isDrawContinue) {
            player.receiveCard(gameCards.drawCard())
            OutputView.printPlayerCard(player)
            handlePlayerDraw(player, gameCards) // 재귀 호출
        }

        if (!player.isDrawContinue && player.cardSize() == previousCardCount) {
            OutputView.printPlayerCard(player)
        }
    }

    companion object {
        private const val FIRST_ROUND_HAND_SIZE = 2
    }
}
