package blackjack.ui

import blackjack.domain.Player

class ResultView {
    fun printStartMessage(players: List<Player>) {
        val playersName = StringBuilder()
        players.forEachIndexed { index, player ->
            playersName.append(player.name)
            if (index != players.lastIndex) {
                playersName.append(", ")
            }
        }
        println("${playersName}에게 2장씩 나누었습니다.")
    }

    fun printPlayerResult(player: Player) {
        print("${player.name}카드: ")

        val lastIndex = player.getAllCards().lastIndex
        player.getAllCards().forEachIndexed { index, card ->
            print(card.printCard())
            if (index != lastIndex) print(", ") else println()
        }
    }

    fun printGameResult(players: List<Player>) {
        players.forEach { player ->
            printPlayerResult(player)
            println(" - 결과: ${player.calculateCard()}")
        }
    }
}
