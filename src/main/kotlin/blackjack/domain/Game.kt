package blackjack.domain

import blackjack.InputView
import blackjack.ResultView

class Game(val players: Players, val dealer: Dealer) {
    init {
        dealer.shuffle()
        deliverInitialCards()
    }

    private fun deliverInitialCards() {
        players.value.forEach {
            List(INITIAL_CARDS_COUNT) { dealer.deliverCard() }
                .let(it::readyToPlay)
        }
    }

    fun play() {
        playPlayers(players, dealer)
        playDealer(dealer)
    }

    private fun playPlayers(players: Players, dealer: Dealer) {
        players.value
            .forEach { player ->
                while (!player.blackjack() &&
                    !player.burst() &&
                    InputView.shouldHit(player)
                ) {
                    player.hit(dealer.deliverCard())
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
