package blackjack.io

import blackjack.domain.Dealer
import blackjack.domain.Player

class Output {
    fun printPlayersCard(players: List<Player>) {
        players.forEach {
            printPlayerCard(it)
        }
    }

    fun printDistribute(players: List<Player>) {
        println("${players.joinToString(",") { it.name }}에게 2장의 나누었습니다.")
    }

    fun printEmptyLine() {
        println()
    }

    fun printPlayerCard(player: Player) {
        println("${player.name} 카드 : ${player.state.hand.cards.joinToString(",")}")
    }

    fun printPlayersHandWithScore(players: List<Player>) {
        players.forEach { player ->
            println("${player.name} 카드 : ${player.state.hand.cards.joinToString(",")} - 결과 : ${player.score()}")
        }
    }

    fun printDealerHandWithScore(dealer: Dealer) {
        println("${dealer.name} 카드 : ${dealer.allOpen().joinToString(",")} - 결과 : ${dealer.score()}")
    }

    fun printDealerDraw() {
        println("딜러는 ${Dealer.DEALERS_HIT_RULE}이하라 한장의 카드를 더 받았습니다.")
    }

    fun printDealerCard(dealer: Dealer) {
        println("딜러 카드 : ${dealer.state.hand.cards.first()}")
    }

    fun printProfitHeader() {
        println("## 최종수익")
    }

    fun printProfit(player: Player, profit: Double) {
        println("${player.name} : $profit")
    }
}
