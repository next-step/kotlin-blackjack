package blackjack

import blackjack.domain.BlackJack
import blackjack.domain.Deck
import blackjack.domain.OnGoingPlayer
import blackjack.domain.Player
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    val players = InputView.getPlayers()

    val blackJack = BlackJack(Deck())

    val finishedPlayers: List<Player> = blackJack.play(players)
        .onEach { OutputView.printCards(it) }
        .map { player ->
            var onGoingPlayer = player

            while (true) {
                if (onGoingPlayer !is OnGoingPlayer) {
                    break
                }

                val isHit = InputView.isHit(onGoingPlayer.name)

                if (!isHit) {
                    break
                }

                onGoingPlayer = blackJack.hit(onGoingPlayer)
            }

            onGoingPlayer
        }

    finishedPlayers.forEach {
        OutputView.printResult(it)
    }
}
