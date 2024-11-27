package blackjack

import blackjack.domain.Deck
import blackjack.domain.Player
import blackjack.ui.InputView
import blackjack.ui.ResultView

class BlackJackGame {
    fun start() {
        val names = InputView.getPlayerNames()
        val players = names.map { Player(it) }

        Deck.shuffle()

        players.forEach { player ->
            player.receiveCard(Deck.drawCard())
            player.receiveCard(Deck.drawCard())
        }

        ResultView.printFirstPhase(players)

        players.forEach { player -> requestDrawCards(player) }

        ResultView.printFinalResult(players)
    }

    private fun requestDrawCards(player: Player) {
        while (player.canDrawCard()) {
            val request = InputView.requestCard(player.name)
            if (request == "n") {
                break
            }
            player.receiveCard(Deck.drawCard())
            ResultView.printPlayerCards(player)
        }
    }
}
