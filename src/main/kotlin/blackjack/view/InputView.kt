package blackjack.view

class InputView {
    fun getPlayers(): List<String> {
        println(INPUT_PLAYERS)
        val players = readln()
        println(players)
        return players.trim().split(SEPARATE_DELIMITER)
    }

    companion object {
        private const val INPUT_PLAYERS = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"

        private const val SEPARATE_DELIMITER = ","
    }
}
