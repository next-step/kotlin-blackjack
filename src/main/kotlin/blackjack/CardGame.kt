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

        players.forEach {
            playTurn(it, cardDeck)
        }

        players.forEach {
            UI.drawResult(it)
        }
    }

    private fun playFirstTurn(players: Players, cardDeck: CardDeck) {
        repeat(2) {
            players.drawAllPlayer(cardDeck)
        }
        UI.drawFirstTurnMessage(players)
        players.list.forEach {
            UI.drawCardList(it)
        }
    }

    private fun playTurn(player: Player, cardDeck: CardDeck) {
        while (player.canDraw() && InputReceiver.receiverWhetherDrawCard(player)) {
            player.draw(cardDeck)
            UI.drawCardList(player)
        }
    }
}

fun main() {
    CardGame.run()
}
