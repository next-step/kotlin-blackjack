package blackjack.domain

import blackjack.view.InputView
import blackjack.view.ResultView

class BlackJackGame(private val deck: Deck = Deck()) {
    fun makeDealer(): Dealer {
        val dealer = Dealer()
        deck.drawInitCards().values.forEach {
            dealer.drawCard(it)
        }

        return dealer
    }

    fun makePlayers(names: List<String>): Players {
        return Players(names.map { Player(it, deck.drawInitCards()) })
    }

    fun play(players: Players) {
        players.values.forEach {
            if (it.isBust()) return@forEach
            drawOrNot(it, deck)
        }
    }

    private fun drawOrNot(player: Player, deck: Deck) {
        while (InputView.inputIsGetCard(player)) {
            player.hit(deck.draw())
            ResultView.printPlayerStatus(player)
            if (player.isBust()) break
        }
    }
}
