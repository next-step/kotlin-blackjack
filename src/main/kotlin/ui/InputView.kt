package ui

object InputView {
    private const val DEFAULT_DELIMITER = ","
    const val YES = "y"
    const val NO = "n"

    fun askPlayerNames(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(${DEFAULT_DELIMITER}기준으로 분리)")
        return readln().split(DEFAULT_DELIMITER).map { it.trim() }
    }

    fun askDrawCardOrNot(playerName: String): String {
        println("$playerName 는 한장의 카드를 더 받겠습니까?(예는 $YES, 아니오는 $NO)")
        val answer = readln()
        require((answer == YES).or(answer == NO)) { "대답은 $YES 또는 $NO 만 가능합니다" }
        return answer
    }
}
