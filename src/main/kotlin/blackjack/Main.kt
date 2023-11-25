package blackjack

import blackjack.domain.ShuffledCardDeck
import blackjack.domain.Player
import blackjack.view.InputView
import blackjack.view.OutputView

fun main() {
    val names = InputView.inputNames()
    val players = names.map { Player(it, ShuffledCardDeck()) }

    OutputView.printPlayersCards(players)
    players.forEach { obtainCard(it) }
    OutputView.printPlayerResult(players)
}

private fun obtainCard(player: Player) {
    while (isObtainCard(player)) {
        player.obtain()
        OutputView.printPlayerCards(player)
    }
}

private fun isObtainCard(player: Player): Boolean {
    return player.isObtainable() && InputView.inputIsObtainCard(player.name)
}
