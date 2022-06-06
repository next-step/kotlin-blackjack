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

    playBlackJack(blackJack)

    OutputView.printResults(blackJack.results)
    OutputView.printBlackJackResult(blackJack.rounds)
}

private fun playBlackJack(blackJack: BlackJack) {
    while (!blackJack.isPlayerAllStay) {
        playerHit(blackJack)
    }

    while (!blackJack.isDealerStay && !blackJack.dealerBust) {
        blackJack.hitDealer { OutputView.printDealerHit() }
    }
}

private fun playerHit(blackJack: BlackJack) {
    blackJack.playerHit {
        when (InputView.inputHitContinue(it.name.value)) {
            Continue.TRUE -> OutputView.printStatus(blackJack.play(it))
            Continue.FALSE -> it.stay()
        }
    }
}
