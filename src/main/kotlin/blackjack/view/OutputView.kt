package blackjack.view

import blackjack.model.Player
import blackjack.model.Players

interface OutputView {
    val printInitCards: (Players) -> Unit
    val printPlayerCards: (Player) -> Unit
    val printResult: (Players) -> Unit
}
