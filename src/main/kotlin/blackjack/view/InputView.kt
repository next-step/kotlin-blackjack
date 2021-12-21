package blackjack.view

import blackjack.domain.Player

object InputView {

    private const val ASK_PLAYERS_NAMES = "게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)"
    private const val ASK_PLAYER_BET_MONEY = "%s의 배팅 금액은?"
    private const val COMMA_DELIMITER = ","
    private const val NULL_POINTER_EXCEPTION_MESSAGE = "입력값은 null일 수 없습니다."
    private const val INPUT_YES = "y"

    fun inputPlayerNames(): List<String> {
        println(ASK_PLAYERS_NAMES)
        return readLine()
            ?.split(COMMA_DELIMITER)
            ?.map { it.trim() }
            ?: throw IllegalArgumentException(NULL_POINTER_EXCEPTION_MESSAGE)
    }

    fun askPlayerWantsToDraw(player: Player): Boolean {
        println("${player.name.name}은 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        return readLine().equals(INPUT_YES)
    }

    fun askPlayerBetMoney(player: Player): String {
        println(ASK_PLAYER_BET_MONEY.format(player.name.name))
        return readLine() ?: throw IllegalArgumentException(NULL_POINTER_EXCEPTION_MESSAGE)
    }
}
