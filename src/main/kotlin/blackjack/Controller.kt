package blackjack

import blackjack.model.Participants
import blackjack.model.Player
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    val participants = InputView.join()
    participants.dealing()
    OutputView.dealing(participants)
    OutputView.presentCards(participants)
    while (participants.isContinue()) {
        playingBlackJack(participants)
    }
    OutputView.result(participants)
}

fun playingBlackJack(participants: Participants) {
    participants.participants.forEach {
        it.hitOrStand()
    }
}

private fun Player.hitOrStand() {
    if (InputView.askHit(this)) {
        this.hit()
    }
    OutputView.playerCardPresent(this)
}
