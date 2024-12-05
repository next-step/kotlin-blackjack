package blackjack.view

object InputView {
    private const val INPUT_PLAYER_NAME_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    private const val INPUT_PICK_CARD_MESSAGE = "%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)"

    fun inputPlayerNames(): String {
        println(INPUT_PLAYER_NAME_MESSAGE)
        return readln()
    }

    fun inputPickCard(playerName: String): String {
        println(INPUT_PICK_CARD_MESSAGE.format(playerName))
        return readln()
    }
}
