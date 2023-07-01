package blackjack.ui

import blackjack.domain.BlackJackGamer
import blackjack.domain.GamerType
import blackjack.domain.Player
import blackjack.domain.card.Card

class ResultView {
    fun printFirstDraw(gamerList: List<BlackJackGamer>) {
        println()
        printFirstDrawTitle(gamerList)
        printFirstDrawDetail(gamerList)
        println()
    }

    private fun printFirstDrawTitle(gamerList: List<BlackJackGamer>) {
        gamerList.forEachIndexed { index, gamer ->
            print(gamer.getName())
            if (index == 0) print("와 ")
            if (index != 0 && index != gamerList.lastIndex) print(", ")
        }
        println("에게 2장의 나누었습니다.")
    }

    private fun printFirstDrawDetail(gamerList: List<BlackJackGamer>) {
        gamerList.forEach {
            printGamerName(it)
            println(getCardsText(it.getCards()))
        }
    }

    private fun printGamerName(gamer: BlackJackGamer) {
        if (gamer.getGamerType() == GamerType.PLAYER) print("${gamer.getName()}카드: ")
        if (gamer.getGamerType() == GamerType.DEALER) print("${gamer.getName()} 카드: ")
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
        println("${player.getName()}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
    }

    fun printGamerCardList(gamer: BlackJackGamer) {
        printGamerName(gamer)
        print(getCardsText(gamer.getCards()))
    }

    fun printGameResult(gamerList: List<BlackJackGamer>) {
        gamerList.forEach {
            printGamerCardList(it)
            printSumOfGamerCardNumbers(it)
        }
    }

    private fun printSumOfGamerCardNumbers(blackJackGamer: BlackJackGamer) {
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
