package blackjack

import blackjack.domain.Card
import blackjack.domain.Player
import blackjack.domain.PlayerState
import blackjack.domain.Players
import blackjack.view.InputView
import blackjack.view.ResultView

fun main() {
    val names: List<String> = InputView().getPlayers()
    val players: Players = Players.of(names)

    ResultView().printStartCard(players)

    players.all().forEach { player ->
        var card: Card? = takeOrBust(player)

        while (card != null) {
            player.hit(card)
            ResultView().printPlayerCard(player)
            card = takeOrBust(player)
        }
    }
    ResultView().printPlayResult(players)
}

private fun takeOrBust(player: Player): Card? {
    val card = InputView().askThePlayer(player) ?: return null

    when (player.takeOrBust(card)) {
        PlayerState.HIT -> return card
        PlayerState.BUST -> ResultView().printBust()
    }
    return null
}
