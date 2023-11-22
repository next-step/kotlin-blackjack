package blackjack.view

object InputView {
    private const val DELIMITER = ","
    private const val MINIMUM_PLAYER = 1
    private const val MAXIMUM_PLAYER = 8

    fun inputPlayers(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        return validateInputPlayer(readln())
    }

    fun inputPlayerChoice(name: String): Boolean {
        print("\n${name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n) ")
        return validateChoice(readln())
    }

    private fun validateInputPlayer(players: String): List<String> {
        val result = players.split(DELIMITER).map { it.trim() }
        require(result.size in MINIMUM_PLAYER..MAXIMUM_PLAYER) { "플레이어 수는 구분자 기준 ${MINIMUM_PLAYER}명에서 ${MAXIMUM_PLAYER}명까지 가능합니다" }
        return result
    }

    private fun validateChoice(choice: String): Boolean {
        require(choice == "y" || choice == "n") { "y 또는 n 으로 입력하세요." }
        return choice == "y"
    }
}
