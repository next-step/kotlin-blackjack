package blackjack

object InputView {
    private const val DELIMITERS = ","
    private val SELECT_REGEX = Regex("^[yn]$")

    tailrec fun inputPlayers(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        val players = readLine()
        return if (players != null && players.contains(DELIMITERS)) {
            players.split(DELIMITERS)
        } else {
            println(",가 포함되어 있어야합니다. 다시 입력해주세요.")
            inputPlayers()
        }
    }

    tailrec fun selectMoreCard(player: String): String {
        println("${player}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        val selectValue = readLine()
        return if (selectValue != null && SELECT_REGEX.matches(selectValue)) {
            selectValue
        } else {
            println("y 와 n 만 선택 가능합니다. 다시 입력해주세요.")
            selectMoreCard(player)
        }
    }
}
