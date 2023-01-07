package blackjack.io

import blackjack.domain.player.Dealer
import blackjack.domain.player.Player
import blackjack.domain.player.User

class Output {
    fun printUsersCard(players: List<User>) {
        players.forEach {
            printPlayerCard(it)
        }
    }

    fun printDistribute(names: List<String>) {
        println("딜러와 ${names.joinToString(",")}에게 2장의 나누었습니다.")
    }

    fun printEmptyLine() {
        println()
    }

    fun printPlayerCard(player: User) {
        println("${player.name} 카드 : ${player.state.cards().joinToString(",")}")
    }

    fun printUsersHandWithScore(players: List<User>) {
        players.forEach { player ->
            println("${player.name} 카드 : ${player.state.cards().joinToString(",")} - 결과 : ${player.score()}")
        }
    }

    fun printDealerHandWithScore(dealer: Dealer) {
        println("${dealer.name} 카드 : ${dealer.state.cards().joinToString(",")} - 결과 : ${dealer.score()}")
    }

    fun printDealerDraw() {
        println("딜러는 ${Dealer.DEALERS_HIT_RULE}이하라 한장의 카드를 더 받았습니다.")
    }

    fun printDealerCard(dealer: Dealer) {
        println("딜러 카드 : ${dealer.state.cards().first()}")
    }

    fun printProfitHeader() {
        println("## 최종수익")
    }

    fun printProfit(player: Player, profit: Double) {
        println("${player.name} : $profit")
    }
}
