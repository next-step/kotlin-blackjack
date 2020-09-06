package blackjack.view

import blackjack.domain.game.Dealer
import blackjack.domain.player.DetailResult
import blackjack.domain.player.HandStatus
import blackjack.domain.player.Player
import blackjack.domain.player.Players

object ResultView {
    private const val GIVE_CARD = "에게 2장의 카드를 나누었습니다."
    private const val DEALER = "딜러"
    private const val DEALER_MORE_CARD = "딜러는 16이하라 %s장의 카드를 더 받았습니다."
    private const val RESULT = "** 최종 승패 **"
    private const val RESULT_DETAIL = "%d승 %d무 %d패"

    fun printGiveCard(players: Players) {
        println("${players.participants.joinToString { it.name }}$GIVE_CARD")
    }

    fun printDealerInitialCard(dealer: Dealer) {
        println("$DEALER : ${dealer.getCards().first()} (${dealer.getScore()})")
    }

    fun printDealerStatus(dealer: Dealer, count: Int) {
        if (count > 0) {
            println(DEALER_MORE_CARD.format(count))
        }
        println("$DEALER : $dealer")
    }

    fun printDealerResult(result: DetailResult) {
        println(RESULT)
        println("$DEALER : ${RESULT_DETAIL.format(result.win, result.push, result.lose)}")
    }

    fun printPlayers(players: Players) {
        println("$players\n")
    }

    fun printPlayersResult(dealer: Dealer, players: Players) {
        players.participants.forEach {
            println("${it.name} : ${it.findResult(dealer)}")
        }
    }

    fun printPlayer(player: Player, handStatus: HandStatus) {
        println("$player\n${handStatus.message}!")
    }
}
