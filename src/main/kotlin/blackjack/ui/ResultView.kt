package blackjack.ui

import blackjack.domain.Player

class ResultView {
    fun printFirstDraw(playerList: List<Player>) {
        println()
        printFirstDrawTitle(playerList)
        printFirstDrawDetail(playerList)
        println()
    }

    private fun printFirstDrawTitle(playerList: List<Player>) {
        playerList.forEachIndexed { index, player ->
            print(player.name)
            if (index != playerList.lastIndex) print(", ")
        }
        println("에게 2장의 나누었습니다.")
    }

    private fun printFirstDrawDetail(playerList: List<Player>) {
        playerList.forEach {
            printPlayerName(it)
            println(it.getCards())
        }
    }

    private fun printPlayerName(player: Player) {
        print("${player.name}카드: ")
    }

    fun printPlayersWantToDrawCard(player: Player) {
        println("${player.name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
    }

    fun printPlayerCardList(player: Player) {
        printPlayerName(player)
        print(player.getCards())
    }

    fun printGameResult(playerList: List<Player>) {
        playerList.forEach {
            printPlayerCardList(it)
            printSumOfPlayerCardNumbers(it)
        }
    }

    private fun printSumOfPlayerCardNumbers(player: Player) {
        val sumOfCardNumbers = player.calculateSumOfCardNumbers()
        println(" - 결과: $sumOfCardNumbers")
    }
}
