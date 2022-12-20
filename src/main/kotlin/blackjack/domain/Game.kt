package blackjack.domain

import blackjack.InputView
import blackjack.ResultView
import blackjack.model.GameResult

class Game(val gamePlayers: GamePlayers, val dealer: Dealer) {
    init {
        dealer.shuffle()
        deliverInitialCards()
    }

    private fun deliverInitialCards() {
        gamePlayers.value.forEach {
            List(INITIAL_CARDS_COUNT) { dealer.deliverCard() }
                .let(it::readyToPlay)
        }
        List(INITIAL_CARDS_COUNT) { dealer.deliverCard() }
            .let(dealer::readyToPlay)
    }

    fun play() {
        playPlayers(gamePlayers, dealer)
        playDealer(dealer)
    }

    private fun playPlayers(gamePlayers: GamePlayers, dealer: Dealer) {
        gamePlayers.value
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
