package blackjack.model

import blackjack.model.card.CardDeck
import blackjack.model.player.Player
import blackjack.view.OutputView

class BlackJackGame(private val players: List<Player>) {
    private val cardDeck = CardDeck()

    fun firstTurn() {
        OutputView.firstTurn(players)

        for (player in players) {
            player.drawCard(cardDeck.pick())
            player.drawCard(cardDeck.pick())
        }

        for (player in players) {
            OutputView.drawCard(player)
        }
    }

    fun progressTurn() {
        for (player in players) {
            progressTurnForPlayer(player)
        }
    }

    private fun progressTurnForPlayer(player: Player) {
        if (player.call()) {
            player.drawCard(cardDeck.pick())
            OutputView.drawCard(player)
        }
    }
}
