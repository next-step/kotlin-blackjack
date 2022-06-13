package blackjack.view

class InputView {

    fun inputPlayers(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        val input = readlnOrNull() ?: throw IllegalArgumentException()
        return input.replace(" ", "").split(PLAYER_NAME_DELIMITER)
    }

    companion object {
        const val PLAYER_NAME_DELIMITER = ","
    }
}
