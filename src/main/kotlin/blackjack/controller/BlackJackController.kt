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
            Player(it).apply { init(trumpCard) }
        }
        OutputView.printInitCard(players.map { it.name })
        OutputView.printPlayersCard(players.map { it.result() })
        players.forEach {
            while (it.burst().not() && InputView.inputHitOrStand(it.name)) {
                it.cards.add(trumpCard.draw())
                OutputView.printPlayerCard(it.result())
            }
            if (it.burst()) OutputView.printPlayerBurst(it.name)
        }
        val result = players.map { it.result() }
        OutputView.printBlackjackGameResult(result)
    }

    private fun Player.result(): BlackJackGameResult {
        return BlackJackGameResult(this)
    }
}
