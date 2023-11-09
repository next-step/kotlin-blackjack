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
        OutputView.printInitCard(players)
        OutputView.printPlayersCard(players)
        players.forEach {
            while (InputView.inputHitOrStand(it)) {
                it.cards.add(trumpCard.draw())
                OutputView.printPlayerCard(it)
            }
        }
        val result = BlackJackGameResult(players)
        OutputView.printBlackjackGameResult(result)
    }
}
