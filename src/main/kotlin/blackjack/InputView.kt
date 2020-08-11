package blackjack

object InputView {
    private const val DELIMITERS = ","

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
}
