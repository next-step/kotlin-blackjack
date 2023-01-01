package blackjack

import blackjack.controller.BlackJackMachine
import blackjack.domain.Participant
import blackjack.view.InputView

fun main() {
    val playerNameList = InputView.readName().split(",")
    val players = playerNameList.map { Participant(name = it) }
    val blackJackMachine = BlackJackMachine(players = players)
    blackJackMachine.initialize()
    blackJackMachine.execute()
}
