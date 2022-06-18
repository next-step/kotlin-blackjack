package blackjack

import blackjack.application.BlackJack
import blackjack.domain.participant.player.Player
import blackjack.domain.participant.player.Players
import blackjack.domain.participant.vo.Name
import blackjack.ui.InputView
import blackjack.ui.OutputView
import blackjack.ui.dto.Continue

fun main() {
    val players = InputView.inputNames()
        .map { Player.sit(Name(it), InputView.inputBetAmount(it)) }
        .let(::Players)
    val blackJack = BlackJack.setup(players)
    blackJack.ready()

    OutputView.printGameReady(blackJack.participantsNames)
    OutputView.printStatuses(blackJack.statuses)

    playBlackJack(blackJack)
    if (blackJack.isDealerBust) {
        OutputView.printDealerBust(blackJack.winningResults())
        return
    }

    OutputView.printResults(blackJack.scores)
    OutputView.printBlackJackResult(blackJack.winningResults())
}

private fun playBlackJack(blackJack: BlackJack) {
    while (!blackJack.hasMorePlayablePlayer && !blackJack.isDealerBust) {
        playerHit(blackJack)
    }

    while (blackJack.isDealerDrawMoreCard && !blackJack.isDealerBust) {
        blackJack.hitDealer()
        OutputView.printDealerHit()
    }
}

private fun playerHit(blackJack: BlackJack) {
    blackJack.hitPlayers {
        when (InputView.inputHitContinue(it.name.value)) {
            Continue.TRUE -> OutputView.printStatus(blackJack.play(it))
            Continue.FALSE -> it.stay()
        }
    }
}
