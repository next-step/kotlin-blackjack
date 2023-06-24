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

    fun printPlayersWantToDrawCard(player: Player) {
        println("${player.name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
    }

    fun printPlayerCardList(player: Player) {
        print("${player.name}카드:")
        println(player.getCards())
    }
}
