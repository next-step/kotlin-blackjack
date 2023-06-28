package blakjack.view

object InputView {
    fun readPlayerNames(): List<String> =
        readLineWithMessage("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
            .split(",")

    fun readHitOrStand(playerName: String): Boolean =
        readCharWithMessage("${playerName}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
            .lowercase() == "y"

    private fun readLineWithMessage(message: String): String {
        println(message)
        return readln()
    }

    private fun readCharWithMessage(message: String): Char {
        println(message)
        return readln().single()
    }

    private fun readln(): String {
        return readlnOrNull() ?: throw IllegalArgumentException("입력 값이 없습니다.")
    }
}
