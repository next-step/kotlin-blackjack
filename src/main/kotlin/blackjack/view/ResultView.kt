package blackjack.view

import blackjack.domain.player.Player
import blackjack.domain.state.GameResult

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

    fun printGameResult(gameResult: GameResult) {
        println("##최종 수익")
        println("딜러: ${gameResult.dealerBalance}")
        gameResult.usersResult.forEach {
            println("${it.key.name}: ${it.key.bettingRevenue(it.value)}")
        }
    }

    fun printResultCards(players: List<Player>) {
        println()
        players.forEach { user ->
            println("${user.name}카드: ${user.cards} - 결과: ${user.points}")
        }
    }
}
