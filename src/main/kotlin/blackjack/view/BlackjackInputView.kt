package blackjack.view

class BlackjackInputView {
    fun getPlayerNames(): String? {
        return getInput("플레이어 이름을 입력하세요. (쉼표 기준 분리)")
    }

    fun getHit(playerName: String): String? {
        return getInput("${playerName}, 한 장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)\n")
    }

    private fun getInput(message: String): String? {
        println(message)

        return readlnOrNull()
    }
}
