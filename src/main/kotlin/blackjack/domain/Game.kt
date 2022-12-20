package blackjack.domain

import blackjack.InputView
import blackjack.ResultView

class Game(val players: Players, val dealer: Dealer) {
    init {
        gameDealer.shuffle()
        deliverInitialCards()
    }

    private fun deliverInitialCards() {
        players.value.forEach {
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
        players.value
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

    private fun playDealer(gameDealer: GameDealer) {
        while (!gameDealer.blackjack() && !gameDealer.stay() && !gameDealer.burst()) {
            InputView.printDealerHit()
            gameDealer.hit(gameDealer.deliverCard())
        }
    }

    companion object {
        const val INITIAL_CARDS_COUNT = 2
    }
}
