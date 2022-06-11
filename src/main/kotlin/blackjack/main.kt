package blackjack

import blackjack.domain.Dealer
import blackjack.domain.Participant
import blackjack.domain.Player
import blackjack.ui.InputView
import blackjack.ui.OutputView

fun main() {
    val dealer = Dealer()
    val participants = participate()

    distribute(dealer, participants)

    deal(dealer, participants)

    dealerHit(dealer)

    OutputView.showResult(listOf(dealer) + participants)
}

private fun deal(dealer: Dealer, participants: List<Participant>) {
    participants.forEach { participant ->
        dealWith(dealer, participant)
    }
}

private tailrec fun dealerHit(dealer: Dealer) {
    if (!dealer.playable() || !dealer.saidHit()) {
        return
    }
    dealer.receive(dealer.draw())
    OutputView.showDealerHit()
    dealerHit(dealer)
}

private tailrec fun dealWith(dealer: Dealer, participant: Participant) {
    if (!participant.playable() || !participant.saidHit()) {
        return
    }
    participant.receive(dealer.draw())
    OutputView.showHand(participant)
    dealWith(dealer, participant)
}

private fun participate(): List<Participant> {
    return InputView.readPlayerNames()
        .map { name ->
            Player(name) { InputView.askHit(name) }
        }
}

private fun distribute(dealer: Dealer, participants: List<Participant>) {
    participants.forEach { participant ->
        participant.receive(dealer.draw())
        participant.receive(dealer.draw())
    }
    dealer.receive(dealer.draw())
    dealer.receive(dealer.draw())
    OutputView.showDistribution(dealer, participants)
}
