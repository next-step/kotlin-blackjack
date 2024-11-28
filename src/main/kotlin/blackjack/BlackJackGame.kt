package blackjack

import blackjack.domain.Deck
import blackjack.domain.Player
import blackjack.ui.InputView
import blackjack.ui.ResultView

object BlackJackGame {
    fun start() {
        val names = InputView.getPlayerNames()
        val players = names.map { Player(it) }

        val deck = Deck()

        players.forEach { player ->
            player.receiveCard(deck.drawCard())
            player.receiveCard(deck.drawCard())
        }

        ResultView.printFirstPhase(players)

        players.forEach { player -> requestDrawCards(player, deck) }

        ResultView.printFinalResult(players)
    }

    private fun requestDrawCards(player: Player, deck: Deck) {
        while (player.canDrawCard()) {
            val request = InputView.requestCard(player.name)
            if (!request) {
                break
            }
            player.receiveCard(deck.drawCard())
            ResultView.printPlayerCards(player)
        }
    }
}
