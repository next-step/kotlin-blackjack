package blackjack.ui

import blackjack.domain.Dealer
import blackjack.domain.Player
import blackjack.domain.card.Card

class ResultView {
    fun printFirstDraw(playerList: List<Player>, dealer: Dealer) {
        println()
        printFirstDrawTitle(playerList)
        printFirstDrawDetail(playerList, dealer)
        println()
    }

    private fun printFirstDrawTitle(playerList: List<Player>) {
        print("딜러와 ")
        playerList.forEachIndexed { index, player ->
            print(player.name)
            if (index != playerList.lastIndex) print(", ")
        }
        println("에게 2장의 나누었습니다.")
    }

    private fun printFirstDrawDetail(playerList: List<Player>, dealer: Dealer) {
        println(getCardsText(dealer.getCards()))

        playerList.forEach {
            printPlayerName(it)
            println(getCardsText(it.getCards()))
        }
    }

    private fun printPlayerName(player: Player) {
        print("${player.name}카드: ")
    }

    private fun getCardsText(cards: List<Card>): String {
        var cardText = ""
        cards.forEachIndexed { index, card ->
            cardText += card.number.toString() + card.shape.value
            if (index != cards.lastIndex) cardText += ", "
        }
        return cardText
    }

    fun printPlayersWantToDrawCard(player: Player) {
        println("${player.name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
    }

    fun printPlayerCardList(player: Player) {
        printPlayerName(player)
        print(getCardsText(player.getCards()))
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

    fun printNextLine() {
        println()
    }
}
