package blackjack

import blackjack.application.BlackJack
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Participant
import blackjack.domain.participant.Player
import blackjack.ui.input.InputView
import blackjack.ui.input.ParticipantView
import blackjack.ui.output.PlayingView
import blackjack.ui.output.ResultView

fun main() {
    val dealer = Dealer()
    val participants = participate()

    val blackjack = BlackJack(dealer, participants)

    blackjack.distribute()
    PlayingView.showDistribution(dealer, participants)

    deal(dealer, participants)

    dealerHit(dealer)

    ResultView.showResultHand(listOf(dealer) + participants)

    val manager = BlackJack(dealer, participants)
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
