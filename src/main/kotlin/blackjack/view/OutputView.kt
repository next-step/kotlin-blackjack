package blackjack.view

import blackjack.model.GameResult
import blackjack.model.Player
import blackjack.model.Players

interface OutputView {
    val printInitCards: (Player, Players) -> Unit
    val printPlayerCards: (Player) -> Unit
    val printDealerDraw: (Player) -> Unit
    val printCardResult: (Player, Players) -> Unit
    val printGameResult: (Players, GameResult) -> Unit
}
