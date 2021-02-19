package blackjack.view

import blackjack.domain.player.Player

object InputView {
    private const val CONTINUE = "y"
    private const val STOP = "n"
    private val ENABLE_ANSWER = listOf(CONTINUE, STOP)

    fun inputPlayerNames(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        val players = readLine()
        require(!players.isNullOrBlank()) { "문자열에 null이나 빈 값이 들어가서는 안됩니다." }

        return players.split(",")
    }

    fun inputBettingFor(playerName: String): Int {
        println("${playerName}의 베팅 금액은?")
        val bet = readLine()
        require(!bet.isNullOrBlank()) { "문자열에 null이나 빈 값이 들어가서는 안됩니다." }

        return bet.toInt()
    }

    fun additionalDraw(playerName: String): Boolean {
        println("${playerName}은(는) 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        val answer = readLine()
        require(!answer.isNullOrBlank()) { "문자열에 null이나 빈 값이 들어가서는 안됩니다." }
        require(ENABLE_ANSWER.contains(answer.trim())) { "y,n 중의 하나를 입력해 주세요." }

        return answer.trim() == CONTINUE
    }
}
