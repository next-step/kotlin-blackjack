package blackjack.view

class InputView {
    fun getPlayerNamesInput() = getIntput(PLAYER_NAMES) ?: throw RuntimeException("플레이어가 입력되지 않았습니다.")
    fun getPlayerRequestInput(playerName: String) = getIntput(playerName + ADDITIONAL_CARD_REQUEST) ?: throw RuntimeException("플레이어가 답변하지 않았습니다.")

    private fun getIntput(message: String): String? {
        println(message)
        val input = readlnOrNull()?.trim()
        if (input.isNullOrEmpty()) return null
        return input
    }

    private companion object {
        const val PLAYER_NAMES = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
        const val ADDITIONAL_CARD_REQUEST = "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"
    }
}
