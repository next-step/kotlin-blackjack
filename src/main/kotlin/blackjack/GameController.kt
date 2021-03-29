package blackjack

import blackjack.domain.participants.Player
import blackjack.ui.InputView
import blackjack.ui.OutputView

fun main() {
    val players = createPlayers()
    OutputView.printAllPlayerCards(players)

    gameStart(players)

    OutputView.printResult(players)
}

fun createPlayers(): List<Player> {
    val names = InputView.inputPlayers()
    return names.map { Player(it) }
}

fun gameStart(players: List<Player>) {
    for(player in players) {
        playerTurn(player)
    }
}

fun playerTurn(player: Player) {
    var answer = "Y"
    while(answer == "Y" && player.checkPlayerCanDraw()) {
        answer = InputView.selectDrawCard(player.name)
        playSelection(player, answer)
    }
}

fun playSelection(player: Player, answer: String) {
    if(answer == "Y") {
        player.drawCard()
        OutputView.printPlayerCards(player)
    }
}
