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

    fun makePlayers(names: List<String>): List<Player> {
        return names.map { Player(it, deck.drawInitCards()) }
    }

    fun play(players: List<Player>, dealer: Dealer) {
        players.forEach {
            if (it.isBust()) return@forEach
            drawOrNot(it, deck)
        }
        hitOrStayForDealer(dealer)
    }

    fun getResult(players: List<Player>, dealer: Dealer): List<PlayerResult> {
        return players.map {
            PlayerResult.from(it, dealer)
        }
    }

    private fun hitOrStayForDealer(dealer: Dealer) {
        if (dealer.isHit()) dealer.drawCard(deck.draw())
    }

    private fun drawOrNot(player: Player, deck: Deck) {
        while (InputView.inputIsGetCard(player)) {
            player.hit(deck.draw())
            ResultView.printPlayerStatus(player)
            if (player.isBust()) break
        }
    }
}
