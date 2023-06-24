package blackjack

import blackjack.domain.Players
import blackjack.view.inputPlayerNames
import blackjack.view.printFirstDrawResult

fun main() {
    val blackJackGame = BlackjackGame(players = Players.from(inputPlayerNames()))

    printFirstDrawResult(blackJackGame.firstDraw())
}
