package interfaces.ui

object OutputConsole {

    private const val NAME_DELIMITER = ", "

    fun printCard(playerName: String, cardInfo: String) {
        println("${playerName}카드: $cardInfo")
    }

    fun printNewLine() {
        println()
    }

    fun printCardWithResult(playerName: String, cardInfo: String, result: String) {
        println("${playerName}카드: $cardInfo - 결과: $result")
    }

    fun printInit(playerNames: List<String>) {
        val playerNameString = playerNames.joinToString(NAME_DELIMITER)
        println()
        println("${playerNameString}에게 2장의 카드를 나누었습니다.")
    }
}
