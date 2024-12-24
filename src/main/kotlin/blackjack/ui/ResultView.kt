package blackjack.ui

import blackjack.domain.player.Dealer
import blackjack.domain.player.Participant
import blackjack.domain.player.Players

class ResultView {
    fun printStartMessage(players: Players) {
        val playersName = StringBuilder()
        players.forEach { player ->
            playersName.append(player.name + ",")
        }
        playersName.deleteCharAt(playersName.lastIndex)
        println("딜러와 ${playersName}에게 2장씩 나누었습니다.")
    }

    fun printGameResult(
        players: Players,
        dealer: Dealer,
    ) {
        showAllCards(dealer)
        println(" - 결과: ${dealer.calculateCard()}")

        players.forEach { player ->
            showAllCards(player)
            println(" - 결과: ${player.calculateCard()}")
        }

        println("\n## 최종 수익")
        printPlayerBalance(dealer)
        players.forEach { player ->
            printPlayerBalance(player)
        }
    }

    fun showCards(player: Participant) {
        if (player.isDealer()) {
            println("${player.name}: ${player.getAllCards()[0].printCard()}")
            return
        }
        showAllCards(player)
    }

    private fun showAllCards(player: Participant) {
        print("${player.name}카드: ")

        val lastIndex = player.getAllCards().lastIndex
        player.getAllCards().forEachIndexed { index, card ->
            print(card.printCard())
            if (index != lastIndex) print(", ") else println()
        }
    }

    private fun printPlayerBalance(player: Participant) {
        println("${player.name}: ${player.balance.toInt()}")
    }

    fun printDealerDrawExtra(participant: Participant) {
        if (participant.isDealer()) {
            println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.\n")
        }
    }
}
