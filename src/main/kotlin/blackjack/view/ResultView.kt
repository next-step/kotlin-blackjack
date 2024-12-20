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
        println("##최종 승패")
        println("${gameResult.winCountDealer}승 ${gameResult.drawCount}무 ${gameResult.loseCountDealer}패")

        gameResult.getGameUserResult().forEach {
            println(it)
        }
    }

    fun printResultCards(
        dealer: Player,
        users: List<Player>,
    ) {
        println()
        println("${dealer.name}카드: ${dealer.cards} - 결과: ${dealer.points}")
        users.forEach { user ->
            println("${user.name}카드: ${user.cards} - 결과: ${user.points}")
        }
    }
}
