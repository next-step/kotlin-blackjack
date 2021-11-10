package blackjack.view

class InputView {

    companion object {
        private const val INPUT_PLAYER_MESSAGE = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
        private const val DELIMITER = ", "

        fun inputPlayers(): List<String> {
            println(INPUT_PLAYER_MESSAGE)
            return readLine()!!.split(DELIMITER)
        }
    }
}
