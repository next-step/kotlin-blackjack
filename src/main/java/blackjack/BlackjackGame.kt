package blackjack

import blackjack.card.CardFactory
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackGame {

    private val gameLeader = GameLeader(CardFactory.makeCards())

    fun start() {
        val players: List<Player> =
            InputView.inputPlayerNames().map { Player(it, gameLeader.giveTwoCards()) }

        players.forEach { OutputView.showCard(it) }

        players.forEach(::needToMoreCard)

        players.forEach {
            OutputView.showResult(it, gameLeader.sum(it.cards))
        }
    }

    private fun needToMoreCard(player: Player) {
        if (!InputView.inputReceiveCardYn(player.name)) return

        player.receiveCard(gameLeader.giveCard())
        OutputView.showCard(player)

        needToMoreCard(player)
    }
}
