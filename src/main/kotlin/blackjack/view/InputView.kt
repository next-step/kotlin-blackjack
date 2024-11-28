package blackjack.view

object InputView {
    private const val INPUT_PLAYER_NAME_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"

    fun inputName(): String {
        println(INPUT_PLAYER_NAME_MESSAGE)
        return readln()
    }
}