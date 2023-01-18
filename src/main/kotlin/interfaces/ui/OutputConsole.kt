package interfaces.ui

import application.BlackJackGame
import domain.player.Dealer
import domain.player.Playable
import domain.player.Player

object OutputConsole {

    private const val NAME_DELIMITER = ", "

    fun printDealerCard(dealer: Dealer) {
        val cardInfo = dealer.cardInfo()
        printCard(playerName = "딜러", cardInfo = cardInfo)
    }

    private fun printCard(playerName: String, cardInfo: List<CardInfo>) {
        println("${playerName}카드: ${cardInfo.toStr()}")
    }

    fun printCardWithResult(allPlayers: List<Player>) {
        allPlayers.forEach {
            val playerName = it.name
            val cardInfo = it.cardInfo()
            val resultScore = it.handsCardScore()
            printCardWithResult(playerName, cardInfo, resultScore.toString())
        }
    }

    private fun printCardWithResult(playerName: String, cardInfo: List<CardInfo>, result: String) {
        println("${playerName}카드: ${cardInfo.toStr()} - 결과: $result")
    }

    fun printInit(allPlayers: List<Player>) {
        val playerNameString = allPlayers.joinToString(NAME_DELIMITER) { it.name }
        println()
        println("딜러와 ${playerNameString}에게 2장의 카드를 나누었습니다.")
    }

    fun printDealerMoreReceiveCard() {
        println()
        println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
    }

    fun printFinalResult(
        resultDealerBoard: BlackJackGame.ResultDealerBoard,
        resultPlayerBoards: List<BlackJackGame.ResultPlayerBoard>
    ) {
        println()
        println("## 최종 승패")
        println("딜러: ${resultDealerBoard.winCount}승 ${resultDealerBoard.totalCount - resultDealerBoard.winCount}패")
        resultPlayerBoards.forEach {
            val result = if (it.isWin) "승" else "패"
            println("${it.name}: $result")
        }
    }


    fun printPlayerCard(players: List<Player>) {
        players.forEach {
            printCard(it)
        }
        println()
    }

    private fun Playable.cardInfo(): List<CardInfo> {
        return this.hands.cardList().map {
            CardInfo(
                number = it.number.nameValue,
                name = it.shape.nameValue
            )
        }
    }

    fun printCard(player: Player) {
        val cardInfo = player.cardInfo()
        printCard(playerName = player.name, cardInfo = cardInfo)
    }

    fun printCardWithDealerResult(dealer: Dealer) {
        println()
        val dealerCardInfo = dealer.cardInfo()
        val resultDealerScore = dealer.handsCardScore()
        printCardWithResult(playerName = "딜러", cardInfo = dealerCardInfo, result = resultDealerScore.toString())
    }
}

data class CardInfo(
    val name: String,
    val number: String
)

private fun List<CardInfo>.toStr(): String {
    return this.joinToString(",") {
        "${it.number}${it.name}"
    }
}
