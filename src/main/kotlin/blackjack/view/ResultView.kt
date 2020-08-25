package blackjack.view

import blackjack.domain.game.Dealer
import blackjack.domain.player.HandStatus
import blackjack.domain.player.Player
import blackjack.domain.player.Players

object ResultView {
    private const val GIVE_CARD = "에게 2장의 카드를 나누었습니다."
    private const val DEALER = "딜러"
    private const val DEALER_MORE_CARD = "딜러는 16이하라 %s장의 카드를 더 받았습니다."
    private const val WINNER = "우승자 :"
    private const val WINNER_IS_DEALER = "아쉽지만 딜러가 이겼어요...!"

    fun printGiveCard(players: Players) {
        println("${players.participants.joinToString { it.name }}$GIVE_CARD")
    }

    fun printDealerInitialCard(dealer: Dealer) {
        println("$DEALER : ${dealer.getCards().first()} (${dealer.getScore()})")
    }

    fun printDealerResult(dealer: Dealer, count: Int) {
        if (count > 0) {
            println(DEALER_MORE_CARD.format(count))
        }
        println("$DEALER : $dealer")
    }

    fun printPlayers(players: Players) {
        println("$players\n")
    }

    fun printWinners(winners: List<Player>) {
        println(if (winners.isEmpty()) WINNER_IS_DEALER else "$WINNER $winners")
    }

    fun printPlayer(player: Player, handStatus: HandStatus) {
        println("$player\n${handStatus.message}!")
    }
}
