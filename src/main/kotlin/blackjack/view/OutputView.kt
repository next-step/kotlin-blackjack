package blackjack.view

import blackjack.model.Player
import blackjack.model.Players

interface OutputView {
    val printInitCards: (Player, Players) -> Unit
    val printPlayerCards: (Player) -> Unit
    val printDealerDraw: (Player) -> Unit
    val printResult: (Player, Players) -> Unit
}
