package blackjack

import blackjack.domain.blackjack.BlackJack
import blackjack.domain.player.Player
import blackjack.domain.player.PlayerStatus
import blackjack.domain.player.Players
import blackjack.view.InputView
import blackjack.view.ResultView

fun main() {
    val players = InputView.players()
    val blackJack = BlackJack(players = Players(players))

    ResultView.printlnBlackJackInit(players)
    ResultView.printlnPlayersWithCards(players)

    val hittablePlayers = blackJack.hittablePlayers
    hittablePlayers.forEach {
        while (!it.isEnd) {
            hitOrStay(blackJack, it, InputView.isHit(it))
        }
    }

    val result = blackJack.result()
    ResultView.printResult(result)
}

fun hitOrStay(blackJack: BlackJack, player: Player, hit: Boolean) {
    if (hit) {
        player.changeStatus(PlayerStatus.HIT)
        blackJack.giveCard(player)
            .also { ResultView.printlnPlayerWithCards(player.name, player.cards) }
    } else {
        player.changeStatus(PlayerStatus.STAY)
    }
}
