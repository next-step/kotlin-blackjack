package blackjack

import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Participant
import blackjack.domain.participant.Player
import blackjack.domain.result.BlackJackManager
import blackjack.ui.input.InputView
import blackjack.ui.input.ParticipantView
import blackjack.ui.output.PlayingView
import blackjack.ui.output.ResultView

fun main() {
    val dealer = Dealer()
    val participants = participate()

    distribute(dealer, participants)

    deal(dealer, participants)

    dealerHit(dealer)

    ResultView.showResultHand(listOf(dealer) + participants)

    val manager = BlackJackManager(dealer, participants)
    val result = manager.matching()

    ResultView.showBlackJackResult(result)
}

private fun deal(dealer: Dealer, participants: List<Participant>) {
    participants.forEach { participant ->
        dealWith(dealer, participant)
    }
}

private tailrec fun dealerHit(dealer: Dealer) {
    if (!dealer.isPlayable()) {
        return
    }
    dealer.receive(dealer.draw())
    PlayingView.showDealerHit()
    dealerHit(dealer)
}

private tailrec fun dealWith(dealer: Dealer, participant: Participant) {
    if (!participant.isPlayable { ParticipantView.askHit(participant.name) }) {
        return
    }
    participant.receive(dealer.draw())
    PlayingView.showOpenHand(participant)
    dealWith(dealer, participant)
}

private fun participate(): List<Participant> {
    return InputView.readPlayerNames()
        .map { name ->
            Player(name)
        }
}

private fun distribute(dealer: Dealer, participants: List<Participant>) {
    participants.forEach { participant ->
        participant.receive(dealer.draw())
        participant.receive(dealer.draw())
    }
    dealer.receive(dealer.draw())
    dealer.receive(dealer.draw())
    PlayingView.showDistribution(dealer, participants)
}
