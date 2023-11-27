package blackjack.view

class BlackjackInputView {
    fun getPlayerNames(): String? {
        return getInput("플레이어 이름을 입력하세요. (쉼표 기준 분리)")
    }

    private fun getInput(message: String): String? {
        println(message)

        return readlnOrNull()
    }
}
