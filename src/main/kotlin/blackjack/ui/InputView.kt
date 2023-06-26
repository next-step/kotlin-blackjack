package blackjack.ui

object InputView {
    private const val DELIMITER = ","
    private const val YES_ANSWER = "y"
    private const val NO_ANSWER = "n"

    fun getPlayerName(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        return readln().split(DELIMITER).toList()
    }

    fun isHit(playerName: String): Boolean {
        println("${playerName}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        val answer = readln()
        require(answer == YES_ANSWER || answer == NO_ANSWER) { "예는 y, 아니오는 n 만 입력할 수 있습니다." }
        return answer == YES_ANSWER
    }
}
