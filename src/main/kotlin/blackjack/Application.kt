package blackjack

import blackjack.domain.Name
import blackjack.domain.Player
import blackjack.view.InputView

fun main() {
    val playersName = InputView.getPlayersName()
    val players = playersName.map { Player(Name(it)) }
}
