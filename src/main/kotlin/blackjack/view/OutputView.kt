package blackjack.view

import blackjack.model.Dealer
import blackjack.model.GameResult
import blackjack.model.Player
import blackjack.model.Players

interface OutputView {
    val printInitCards: (Dealer, Players) -> Unit
    val printPlayerCards: (Player) -> Unit
    val printCardResult: (Dealer, Players) -> Unit
    val printDealerDraw: (Dealer) -> Unit
    val printGameResult: (Players, GameResult) -> Unit
}
