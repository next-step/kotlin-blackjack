package blackjack

import blackjack.application.BlackJack
import blackjack.domain.player.Name
import blackjack.domain.player.Player
import blackjack.domain.player.Players
import blackjack.ui.InputView
import blackjack.ui.OutputView
import blackjack.ui.dto.Continue

fun main() {
    val players = InputView.inputNames()
        .map { Player.sit(Name(it)) }
        .let(::Players)
    val blackJack = BlackJack.setup(players)

    blackJack.ready()
    OutputView.printGameReady(blackJack.statuses.map { it.name })

    blackJack.statuses.forEach { OutputView.printStatus(it) }
    while (!players.allStay) {
        hitPlayers(players, blackJack)
    }
    blackJack.statuses.forEach { OutputView.printResult(it) }
}

private fun hitPlayers(players: Players, blackJack: BlackJack) {
    players.players.filterNot { it.isStay }
        .forEach {
            when (InputView.inputHitContinue(it.name.value)) {
                Continue.TRUE -> OutputView.printStatus(blackJack.play(it))
                Continue.FALSE -> it.stay()
            }
        }
}
