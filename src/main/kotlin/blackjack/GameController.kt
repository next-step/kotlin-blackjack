package blackjack

import blackjack.domain.participants.Dealer
import blackjack.domain.participants.Participant
import blackjack.domain.participants.Player
import blackjack.ui.InputView
import blackjack.ui.OutputView

fun main() {
    val players = createPlayers()
    val dealer = Dealer()
    OutputView.printAllPlayerCards(players)

    gameStart(players)

    OutputView.printResult(players)
}

fun createPlayers(): List<Participant> {
    val names = InputView.inputPlayers()
    return names.map { Player(it) }
}

fun gameStart(participants: List<Participant>) {
    for(participant in participants) {
        playerTurn(participant)
    }
}

fun playerTurn(participant: Participant) {
    var answer = "Y"
    while(answer == "Y" && participant.checkCardDrawAvailable()) {
        answer = InputView.selectDrawCard(participant.name)
        playSelection(participant, answer)
    }
}

fun playSelection(participant: Participant, answer: String) {
    if(answer == "Y") {
        participant.drawCard()
        OutputView.printPlayerCards(participant)
    }
}
