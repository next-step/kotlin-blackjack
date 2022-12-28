package blackjack.view

import blackjack.model.Player

interface OutputView {
    val printInitCards: (List<Player>) -> Unit
    val printPlayerCards: (Player) -> Unit
    val printResult: (List<Player>) -> Unit
}
