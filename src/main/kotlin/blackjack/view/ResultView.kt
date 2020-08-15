package blackjack.view

import blackjack.domain.player.HandStatus
import blackjack.domain.player.Player
import blackjack.domain.player.Players

object ResultView {
    private const val GIVE_CARD = "에게 2장의 카드를 나누었습니다."
    private const val WINNER = "우승자 :"

    fun printGiveCard(players: Players) {
        println("${players.participants.joinToString { it.name }}$GIVE_CARD")
    }

    fun printPlayers(players: Players) {
        println("---------------------")
        println("$players")
        println("---------------------")
    }

    fun printWinners(winners: List<Player>) {
        println("$WINNER $winners")
    }

    fun printPlayer(player: Player, handStatus: HandStatus) {
        println("$player\n${handStatus.message}!")
    }
}
