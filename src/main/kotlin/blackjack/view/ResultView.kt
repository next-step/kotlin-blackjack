package blackjack.view

import blackjack.domain.player.HandStatus
import blackjack.domain.player.Player
import blackjack.domain.player.Players

object ResultView {
    private const val GIVE_CARD = "에게 2장의 카드를 나누었습니다."
    private const val ERR_INVALID_NAME = "1명 이상의 이름을 입력해주세요."
    private const val WINNER = "우승자 :"

    fun printInvalidName() {
        println(ERR_INVALID_NAME)
    }

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
