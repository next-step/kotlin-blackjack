package blackjack.view

import blackjack.domain.player.Dealer
import blackjack.domain.player.GameUser
import blackjack.domain.player.Player

object ResultView {
    fun printMessage(message: Any) {
        println("$message")
    }

    fun printStartGame(
        dealer: Player,
        users: List<Player>,
        count: Int,
    ) {
        println()
        println("${dealer.name}와 ${users.joinToString { it.name }}에게 ${count}장을 나누었습니다.")
    }

    fun printPlayerCards(player: Player) {
        println("${player.name}카드: ${player.getFirstCards()}")
    }

    fun printGameResult(
        dealer: Dealer,
        users: List<GameUser>,
    ) {
        println("##최종 수익")
        println("${dealer.name}: ${dealer.revenueMoney}")
        users.forEach {
            println("${it.name}: ${it.bettingRevenue()}")
        }
    }

    fun printResultCards(players: List<Player>) {
        println()
        players.forEach { user ->
            println("${user.name}카드: ${user.cards} - 결과: ${user.points}")
        }
    }
}
