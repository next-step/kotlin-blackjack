package blackjack

import blackjack.application.BlackJack
import blackjack.domain.player.Player
import blackjack.domain.player.Players
import blackjack.domain.player.vo.Name
import blackjack.ui.InputView
import blackjack.ui.OutputView
import blackjack.ui.dto.Continue

fun main() {
    val players = InputView.inputNames()
        .map { Player.sit(Name(it)) }
        .let(::Players)
    val blackJack = BlackJack.setup(players)
    blackJack.ready()

    OutputView.printGameReady(blackJack.names)
    OutputView.printStatuses(blackJack.statuses)

    while (!blackJack.isPlayerAllStay) {
        hit(blackJack)
    }

    while (!blackJack.isDealerStay) {
        blackJack.hitDealer { OutputView.printDealerHit() }
    }

    OutputView.printResults(blackJack.results)
}

private fun hit(blackJack: BlackJack) {
    blackJack.hit {
        when (InputView.inputHitContinue(it.name.value)) {
            Continue.TRUE -> OutputView.printStatus(blackJack.play(it))
            Continue.FALSE -> it.stay()
        }
    }
}
