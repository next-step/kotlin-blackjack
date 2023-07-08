package blackjack.ui

import blackjack.domain.card.Card
import blackjack.domain.gamer.BlackJackGamer
import blackjack.domain.gamer.Dealer
import blackjack.domain.gamer.GamerType
import blackjack.domain.gamer.Player

class ResultView {
    fun printFirstDraw(gamerList: List<BlackJackGamer>) {
        printFirstDrawTitle(gamerList)
        printFirstDrawDetail(gamerList)
        println()
    }

    private fun printFirstDrawTitle(gamerList: List<BlackJackGamer>) {
        gamerList.forEachIndexed { index, gamer ->
            print(gamer.name)
            if (index == 0) print("와 ")
            if (index != 0 && index != gamerList.lastIndex) print(", ")
        }
        println("에게 2장의 나누었습니다.")
    }

    private fun printFirstDrawDetail(gamerList: List<BlackJackGamer>) {
        gamerList.forEachIndexed { index, gamer ->
            printGamerName(gamer)
            if (index == 0) println(getDealerFlutteringCards(gamer.getCards()))
            if (index != 0) println(getCardsText(gamer.getCards()))
        }
    }

    private fun printGamerName(gamer: BlackJackGamer) {
        if (gamer.getGamerType() == GamerType.PLAYER) print("${gamer.name}카드: ")
        if (gamer.getGamerType() == GamerType.DEALER) print("${gamer.name} 카드: ")
    }

    private fun getDealerFlutteringCards(cards: List<Card>): String {
        val (shape, number) = cards[0]
        return "${number.value}${shape.value}"
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

    fun printGamerCardList(gamer: BlackJackGamer) {
        printGamerName(gamer)
        print(getCardsText(gamer.getCards()))
    }

    fun printDrawResult(gamerList: List<BlackJackGamer>) {
        gamerList.forEach {
            printGamerCardList(it)
            printSumOfGamerCardNumbers(it)
        }
        println()
    }

    private fun printSumOfGamerCardNumbers(blackJackGamer: BlackJackGamer) {
        val sumOfCardNumbers = blackJackGamer.calculateSumOfCardNumbers()
        println(" - 결과: $sumOfCardNumbers")
    }

    fun printNextLine() {
        println()
    }

    fun printDealerIsDraw() {
        println("딜러는 ${Dealer.CONDITION_TO_DEALER_DRAW_CARD}이하라 한장의 카드를 더 받았습니다.")
        println()
    }

    fun printGameResult(gamerList: List<BlackJackGamer>) {
        println("## 최종 승패")
        gamerList.forEach {
            print("${it.name}: ")
            printGameScore(it)
        }
    }

    private fun printGameScore(gamer: BlackJackGamer) {
        if (gamer is Player) println(gamer.getGameRecord().value)
        if (gamer is Dealer) printDealerRecords(gamer)
    }

    private fun printDealerRecords(dealer: Dealer) {
        val totalGameRecord = dealer.getTotalGameRecord()
        totalGameRecord.forEachIndexed { index, pair ->
            print("${pair.second}${pair.first.value}")
            if (index != totalGameRecord.lastIndex) print(" ")
        }
        println()
    }
}
