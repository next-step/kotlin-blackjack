package blackjack.view

object InputView {
    private const val MESSAGE_INPUT_PLAYER_NAMES = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"

    fun inputPlayerNames(): List<String> {
        println(MESSAGE_INPUT_PLAYER_NAMES)
        return requireNotNull(readln().split(",").map { it.trim() })
    }
}
