package blackjack

import blackjack.domain.CardDeck
import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.ui.InputReceiver
import blackjack.ui.UI

object CardGame {
    fun run() {
        val players = InputReceiver.receiverPlayers()
        val cardDeck = CardDeck.new()

        playFirstTurn(players, cardDeck)

        players.list.forEach {
            playTurn(it, cardDeck)
        }
    }

    private fun playFirstTurn(players: Players, cardDeck: CardDeck) {
        repeat(2) {
            players.drawAllPlayer(cardDeck)
        }
        UI.drawFirstTurnMessage(players)
        UI.drawCardList(players)
    }

    private fun playTurn(player: Player, cardDeck: CardDeck) {
        do {
            val isDrawCard = InputReceiver.receiverWhetherDrawCard(player)
            player.draw(cardDeck)
        } while (isDrawCard)
    }
}

fun main() {
    CardGame.run()
}
