package interfaces.ui

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
        println("${playerNameString}에게 2장의 카드를 나누었습니다.")
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
