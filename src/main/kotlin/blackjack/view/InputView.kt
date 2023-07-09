package blackjack.view

object InputView {
    private const val PLAYER_NAME_DELIMITER = ","

    fun receivePlayerNames(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")

        return receiveString().split(PLAYER_NAME_DELIMITER).map { it.trim() }
    }

    fun willReceiveCard(playerName: String): Boolean {
        println("${playerName}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")

        var answer: Boolean? = null

        do {
            val text = receiveString()
            if (text == "y") {
                answer = true
            }

            if (text == "n") {
                answer = false
            }
        } while (answer == null)

        return answer
    }

    private fun receiveString(): String {
        var input: String? = null

        do {
            input = readlnOrNull()
        } while (input.isNullOrBlank())

        return input
    }
}
