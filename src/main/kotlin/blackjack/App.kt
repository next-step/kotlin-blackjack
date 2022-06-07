package blackjack

import blackjack.domain.BlackJack
import blackjack.view.InputView
import blackjack.view.ResultView

fun main() {
    val players = InputView.players()
    val blackJack = BlackJack(players = players)

    ResultView.printlnBlackJackInit(players)
    ResultView.printlnPlayersWithCards(players)

    while (!blackJack.isEnd) {
        val hittablePlayers = blackJack.hittablePlayers()
        val player = hittablePlayers.first()
        when (InputView.isHit(player)) {
            true -> blackJack.hit(player)
                .also { ResultView.printlnPlayerWithCards(player.name, player.cards.cards) }
            false -> player.stay()
        }
    }

    val result = blackJack.result()
    ResultView.printResult(result)
}
