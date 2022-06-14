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

    deal(blackjack, dealer, participants)

    ResultView.showResultHand(listOf(dealer) + participants)

    val result = blackjack.matching()

    ResultView.showBlackJackResult(result)
}

private fun deal(blackJack: BlackJack, dealer: Dealer, participants: List<Participant>) {
    participants.forEach { participant ->
        blackJack.dealWith(participant, ParticipantView::askHit, PlayingView::showOpenHand)
    }
    blackJack.dealWith(dealer, { true }, PlayingView::showDealerHit)
}

private fun participate(): List<Participant> {
    return InputView.readPlayerNames()
        .map { name ->
            Player(name)
        }
}
