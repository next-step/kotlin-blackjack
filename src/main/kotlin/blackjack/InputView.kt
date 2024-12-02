package blackjack

object InputView {
    private const val SEPARATOR = ","
    private const val YES = "y"

    fun getPlayers(): List<Player> {
        val userNames = getUserNames()
        return userNames.map { Player(it) }
    }

    fun isDrawingCard(player: Player): Boolean {
        println("${player}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        return readln() == YES
    }

    private fun getUserNames(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        return readln().split(SEPARATOR)
    }
}
