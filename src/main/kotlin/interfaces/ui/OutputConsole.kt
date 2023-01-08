package interfaces.ui

import application.BlackJackGame

object OutputConsole {

    private const val NAME_DELIMITER = ", "

    fun printCard(playerName: String, cardInfo: List<CardInfo>) {
        println("${playerName}카드: ${cardInfo.toStr()}")
    }

    fun printNewLine() {
        println()
    }

    fun printCardWithResult(playerName: String, cardInfo: List<CardInfo>, result: String) {
        println("${playerName}카드: ${cardInfo.toStr()} - 결과: $result")
    }

    fun printInit(playerNames: List<String>) {
        val playerNameString = playerNames.joinToString(NAME_DELIMITER)
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
