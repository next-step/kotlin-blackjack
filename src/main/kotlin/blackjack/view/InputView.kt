package blackjack.view

object InputView {
    private const val RETRY = "y"
    private const val RETRY_NOT = "n"

    fun readName(): String {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")

        return readln()
    }

    fun hitOrNot(playerName: String): Boolean {
        println("$playerName 는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        val answer = readln()
        require(answer in listOf(RETRY, RETRY_NOT)) { "y 또는 n으로 선택해주세요." }

        return answer == RETRY
    }
}
