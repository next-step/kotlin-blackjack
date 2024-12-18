package blackjack.view

import blackjack.domain.player.Player

class ResultView {
    fun printPlayerCards(player: Player) {
        println("${player.name}카드: ${player.cards}")
    }

    fun printResultCards(
        users: List<Player>,
        dealer: Player,
    ) {
        println()
        println("${dealer.name}카드: ${dealer.cards} - 결과: ${dealer.points}")
        users.forEach { user ->
            println("${user.name}카드: ${user.cards} - 결과: ${user.points}")
        }

        println("##최종 승패")
        val winCount =
            users.filter {
                dealer.comparePoints(it)
            }.size
        println("${dealer.name}: ${winCount}승 ${users.size - winCount}패")
        users.forEach { user ->
            println("${user.name}: ${getUserResultString(dealer.comparePoints(user))}")
        }
    }

    private fun getUserResultString(resultStatus: Boolean): String {
        return if (resultStatus) "승" else "패"
    }
}
