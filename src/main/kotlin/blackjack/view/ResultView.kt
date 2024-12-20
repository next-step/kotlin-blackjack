package blackjack.view

import blackjack.domain.player.Dealer
import blackjack.domain.player.GameUser
import blackjack.domain.player.Player
import blackjack.domain.state.ResultState

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
        val usersResultMessageBuilder = StringBuilder()

        var dealerWinCount = 0
        var dealerLostCount = 0
        var dealerDrawCount = 0
        users.forEach {
            val resultState = it.resultState(dealer)
            when (resultState) {
                ResultState.WIN -> dealerLostCount++
                ResultState.LOSE -> dealerWinCount++
                else -> dealerDrawCount++
            }
            usersResultMessageBuilder.append("${it.name}: ${resultState.displayMessage}\n")
        }

        println("##최종 승패")
        println("${dealer.name}: ${dealerWinCount}승 ${dealerDrawCount}무 ${dealerLostCount}패")
        println(usersResultMessageBuilder.toString())
    }

    fun printResultCards(players: List<Player>) {
        println()
        players.forEach { user ->
            println("${user.name}카드: ${user.cards} - 결과: ${user.points}")
        }
    }
}
