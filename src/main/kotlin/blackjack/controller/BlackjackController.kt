package blackjack.controller

import blackjack.domain.Deck
import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.view.InputView
import blackjack.view.ResultView

class BlackjackController {
    fun run() {
        val deck = Deck()
        val players = setPlayers(deck)
        ResultView.printInitialStatus(players)
        play(deck, players)
        ResultView.printResult(players)
    }

    private fun play(deck: Deck, players: Players) {
        players.values.forEach {
            if (it.isBust()) return@forEach
            drawOrNot(it, deck)
        }
    }

    private fun drawOrNot(player: Player, deck: Deck) {
        while (InputView.inputIsGetCard(player)) {
            player.getCards(deck.draw())
            ResultView.printPlayerStatus(player)
            if (player.isBust()) break
        }
    }

    private fun setPlayers(deck: Deck): Players {
        val names = InputView.inputPlayersName()
        val players = names.map { Player(it, deck.drawInitCards()) }
        return Players(players)
    }
}

fun main() {
    BlackjackController().run()
}
