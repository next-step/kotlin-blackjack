package view

class InputView {

    fun getPlayerNames(): List<String> {
        println(PLAYER_NAME_INPUT_MESSAGE)
        val inputNames = readln()
        return inputNames.split(DELIMITERS)
    }

    fun getPlayerBetAmounts(playerName: List<String>): Map<String, Int> {
        return playerName.associateWith { name ->
            println("$name$BET_AMOUNT_MESSAGE")
            val betAmount = readln().toIntOrNull() ?: 0
            println()
            betAmount
        }
    }

    fun askDraw(playerName: String): Answer {
        println("$playerName $ASK_DRAW_MESSAGE")
        return when (readln().lowercase()) {
            Answer.YES.value -> Answer.YES
            Answer.NO.value -> Answer.NO
            else -> Answer.UNKNOWN
        }
    }

    companion object {
        const val ASK_DRAW_MESSAGE = "은(는) 한 장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"
        const val PLAYER_NAME_INPUT_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
        const val BET_AMOUNT_MESSAGE = "의 배팅 금액은?"
        const val DELIMITERS = ","
    }
}
