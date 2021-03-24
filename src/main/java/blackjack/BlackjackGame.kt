package blackjack

import blackjack.card.CardFactory
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackGame {

    private val gameLeader = GameLeader(CardFactory.makeCards())

    fun start() {
        val players: List<Player> = InputView.inputPlayerNames().map {
            Player(it, gameLeader.giveTwoCards())
        }

        OutputView.showPlayersCard(players)

        players.forEach(gameLeader::needToMoreCard)

        OutputView.showResult(players)
    }
}
