package blackjack.controller

import blackjack.ui.InputView
import blackjack.ui.ResultView

class BlackJackGame {
}

fun main() {
    val participants = InputView.registerParticipants()
    ResultView.showInitialStatusOfParticipants(participants)
}