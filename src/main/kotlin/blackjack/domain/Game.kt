package blackjack.domain

import blackjack.InputView
import blackjack.ResultView

class Game(val players: Players, val dealer: Dealer) {
    init {
        gameDealer.shuffle()
        deliverInitialCards()
    }

    private fun deliverInitialCards() {
        players.gamePlayers.forEach {
            List(INITIAL_CARDS_COUNT) { dealer.deliverCard() }
                .let(it::readyToPlay)
        }
        List(INITIAL_CARDS_COUNT) { gameDealer.deliverCard() }
            .let(gameDealer::readyToPlay)
    }

    fun play() {
        playPlayers(players, dealer)
        playDealer(dealer)
    }

    private fun playPlayers(players: Players, dealer: Dealer) {
        players.gamePlayers
            .forEach { player ->
                while (!player.blackjack() &&
                    !player.burst() &&
                    InputView.shouldHit(player)
                ) {
                    player.hit(gameDealer.deliverCard())
                    ResultView.printPlayerCards(player)
                }
            }
        println()
    }

    private fun playDealer(dealer: Dealer) {
        while (!dealer.blackjack() && !dealer.stay() && !dealer.burst()) {
            InputView.printDealerHit()
            dealer.hit(dealer.deliverCard())
        }
    }

    companion object {
        const val INITIAL_CARDS_COUNT = 2
    }
}
