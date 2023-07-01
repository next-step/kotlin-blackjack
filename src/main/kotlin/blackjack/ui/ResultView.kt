package blackjack.ui

import blackjack.domain.BlackJackGamer
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
        playerList.forEachIndexed { index, player ->
            if (index == 0) print("딜러와 ")
            if (index != 0) print(player.name)
            if (index != playerList.lastIndex) print(", ")
        }
        println("에게 2장의 나누었습니다.")
    }

    private fun printFirstDrawDetail(playerList: List<Player>, dealer: Dealer) {
        printDealerCardList(dealer)
        println()

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

    private fun printDealerCardList(dealer: Dealer) {
        print("딜러: ")
        print(getCardsText(dealer.getCards()))
    }

    fun printGameResult(playerList: List<Player>, dealer: Dealer) {
        printDealerCardList(dealer)
        printSumOfPlayerCardNumbers(dealer)
        playerList.forEach {
            printPlayerCardList(it)
            printSumOfPlayerCardNumbers(it)
        }
    }

    private fun printSumOfPlayerCardNumbers(blackJackGamer: BlackJackGamer) {
        val sumOfCardNumbers = blackJackGamer.calculateSumOfCardNumbers()
        println(" - 결과: $sumOfCardNumbers")
    }

    fun printNextLine() {
        println()
    }

    fun printDealerIsDraw() {
        println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
        println()
    }
}
