package blackjack.controller

import blackjack.domain.Player
import blackjack.domain.TrumpCard
import blackjack.view.BlackJackGameResult
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackJackController {

    fun start() {
        val trumpCard = TrumpCard.init()
        val playerNames = InputView.inputPlayerName()
        val players = playerNames.map {
            Player(it, trumpCard.firstCardDraw())
        }
        OutputView.printInitCard(players.map { it.name })
        OutputView.printPlayersCard(players.map { it.result() })
        players.forEach {
            while (it.isHit() && InputView.inputHitOrStand(it.name)) {
                it.drawBy(trumpCard)
                OutputView.printPlayerCard(it.result())
            }
            if (it.isBurst()) OutputView.printPlayerBurst(it.name)
        }
        val result = players.map { it.result() }
        OutputView.printBlackjackGameResult(result)
    }

    private fun Player.result(): BlackJackGameResult {
        return BlackJackGameResult(this)
    }
}
