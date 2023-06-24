package blackjack.ui

import blackjack.domain.Player

class ResultView {
    fun printFirstDraw(playerList: List<Player>) {
        println()
        printFirstDrawTitle(playerList)
        printFirstDrawDetail(playerList)
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
            print("${it.name}카드:")
            println(it.getCards())
        }
    }
}
