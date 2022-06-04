package blackjack.view

class PlayerNameInputView(private val io: IO) {

    fun run(): List<String> {
        io.print("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        val players = readPlayerNames()
        io.print("")

        return players
    }

    private tailrec fun readPlayerNames(): List<String> {
        val names = io.read()
            .split(NAME_DELIMITER)
            .filter { it.isNotBlank() }

        if (names.isEmpty()) {
            io.print("잘못 입력하셨습니다. 다시 입력하세요.")
            return readPlayerNames()
        }

        return names
    }

    companion object {
        private const val NAME_DELIMITER = ","
    }
}
