package blackjack

import blackjack.domain.blackjack.BlackJack
import blackjack.domain.blackjack.BlackJack.Companion.BASE_CARD_COUNT
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

    playPlayers(blackJack, blackJack.players.hittablePlayers())
    playDealer(blackJack, dealer)

    val result = blackJack.result()
    ResultView.printResult(result)
    ResultView.printMatch(result)
}

private fun playPlayers(
    blackJack: BlackJack,
    hittablePlayers: List<Player>
) {
    hittablePlayers.forEach { player -> playPlayer(player, blackJack) }
}

private fun playPlayer(player: Player, blackJack: BlackJack) {
    while (!player.isEnd()) {
        player.changeStatus(InputView.isHit(player))
        blackJack.play(player).also { printHitPlayer(player) }
    }
}

private fun printHitPlayer(player: Player) {
    if (player.playerStatus == PlayerStatus.HIT) {
        ResultView.printlnPlayerWithCards(player.name, player.cards.cards)
    }
}

private fun playDealer(blackJack: BlackJack, dealer: Dealer) {
    blackJack.playDealer()
    ResultView.printDealerPlay(dealer.cards.cards.size - BASE_CARD_COUNT)
}
