package blackjack.io

import blackjack.domain.Dealer
import blackjack.domain.Player
import blackjack.domain.Result

class Output {
    fun printPlayersCard(players: List<Player>) {
        println("${players.joinToString(",") { it.name }}에게 2장의 나누었습니다.")
        players.forEach {
            printPlayerCard(it)
        }
    }

    fun printEmptyLine() {
        println()
    }

    fun printPlayerCard(player: Player) {
        println("${player.name} 카드 : ${player.open().joinToString(",")}")
    }

    fun printPlayersHandWithScore(players: List<Player>) {
        players.forEach { player ->
            println("${player.name} 카드 : ${player.open().joinToString(",")} - 결과 : ${player.score().value}")
        }
    }

    fun printDealerHandWithScore(dealer: Dealer) {
        println("${dealer.name} 카드 : ${dealer.allOpen().joinToString(",")} - 결과 : ${dealer.score().value}")
    }

    fun printDealerResult(dealer: Dealer, result: List<Result>) {
        println("${dealer.name} : ${Result.WIN.value} ${result.count { it == Result.WIN }} ${Result.LOSE.value} ${result.count { it == Result.LOSE }}")
    }

    fun printPlayersResult(players: List<Player>, dealer: Dealer) {
        players.forEach {
            println("${it.name} : ${it.result(dealer.score()).value}")
        }
    }

    fun printDealerDraw() {
        println("딜러는 ${Dealer.DEALERS_HIT_RULE}이하라 한장의 카드를 더 받았습니다.")
    }
}
