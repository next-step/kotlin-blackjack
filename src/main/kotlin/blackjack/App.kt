package blackjack

import blackjack.domain.blackjack.BlackJack
import blackjack.domain.player.Dealer
import blackjack.domain.player.Player
import blackjack.domain.player.PlayerStatus
import blackjack.domain.player.Players
import blackjack.view.InputView
import blackjack.view.ResultView

fun main() {
    val players = InputView.players()
    val dealer = Dealer()
    val blackJack = BlackJack(dealer = dealer, players = Players(players))

    ResultView.printlnBlackJackInit(players, dealer)
    ResultView.printlnPlayersWithCards(players, dealer)

    val hittablePlayers = blackJack.hittablePlayers
    playPlayers(blackJack, hittablePlayers)
    playDealer(blackJack, dealer)

    val result = blackJack.result()
    ResultView.printResult(result)
    ResultView.printMatch(result)
}

private fun playPlayers(
    blackJack: BlackJack,
    hittablePlayers: List<Player>
) {
    hittablePlayers.forEach {
        while (!it.isEnd) {
            hitOrStay(blackJack, it, InputView.isHit(it))
        }
    }
}

private fun hitOrStay(blackJack: BlackJack, player: Player, hit: Boolean) {
    if (hit) {
        player.changeStatus(PlayerStatus.HIT)
        blackJack.giveCard(player)
            .also { ResultView.printlnPlayerWithCards(player.name, player.cards) }
    } else {
        player.changeStatus(PlayerStatus.STAY)
    }
}

private fun playDealer(blackJack: BlackJack, dealer: Dealer) {
    blackJack.playDealer()
    ResultView.printDealerPlay(dealer.cards.size - 2)
}
