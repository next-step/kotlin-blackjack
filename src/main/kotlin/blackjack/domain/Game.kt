package blackjack.domain

import blackjack.InputView
import blackjack.ResultView
import blackjack.model.GameResult

class Game(val gamePlayers: GamePlayers, val gameDealer: GameDealer) {
    init {
        gameDealer.shuffle()
        deliverInitialCards()
    }

    private fun deliverInitialCards() {
        gamePlayers.value.forEach {
            List(INITIAL_CARDS_COUNT) { gameDealer.deliverCard() }
                .let(it::readyToPlay)
        }
        List(INITIAL_CARDS_COUNT) { gameDealer.deliverCard() }
            .let(gameDealer::readyToPlay)
    }

    fun play() {
        playPlayers(gamePlayers, gameDealer)
        playDealer(gameDealer)
    }

    private fun playPlayers(gamePlayers: GamePlayers, gameDealer: GameDealer) {
        gamePlayers.value
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
