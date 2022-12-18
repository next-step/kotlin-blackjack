package blackjack.domain

import blackjack.view.InputView
import blackjack.view.ResultView

class BlackJackGame(
    private val deck: Deck = Deck(),
    val dealer: Dealer = Dealer(),
    var players: List<Player> = emptyList(),
    var playerResults: List<PlayerResult> = emptyList()
) {
    fun setInitDealer() {
        deck.drawInitCards().values.forEach {
            dealer.hit(it)
        }
    }

    fun setInitPlayers(names: List<String>) {
        this.players = names.map { Player(it, deck.drawInitCards()) }
    }

    fun play() {
        players.forEach {
            if (it.isBust()) return@forEach
            drawOrNot(it, deck)
        }
        hitOrStayForDealer(dealer)
    }

    fun calculateResult() {
        playerResults = players.map {
            PlayerResult.from(it, dealer)
        }
    }

    private fun hitOrStayForDealer(dealer: Dealer) {
        if (dealer.isHit()) dealer.hit(deck.draw())
    }

    private fun drawOrNot(player: Player, deck: Deck) {
        while (InputView.inputIsGetCard(player)) {
            player.hit(deck.draw())
            ResultView.printPlayerStatus(player)
            if (player.isBust()) break
        }
    }
}
