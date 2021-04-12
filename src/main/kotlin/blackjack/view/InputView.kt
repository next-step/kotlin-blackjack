package blackjack.view

object InputView {
    fun readNames(): List<String> {
        val input = readLineWhileNullOrBlank("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")

        return input.split(",")
    }

    private fun readLineWhileNullOrBlank(message: String): String {
        var input: String? = null
        while (input.isNullOrBlank()) {
            println(message)
            input = readLine()
        }

        return input
    }

    fun readUserResponse(gamerName: String): Boolean {
        val input = readLineWhileNullOrBlank("${gamerName}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")

        return input.trim().toLowerCase() == "y"
    }
}
