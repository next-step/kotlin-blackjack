package camp.nextstep.edu.step.step2.view

object InputView {

    private const val YES = "y"
    private const val NO = "n"
    private const val COMMA = ","

    /**
     * @description : 게임에 참여할 유저를 입력받는다.
     */
    fun enterPlayers(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        return readln().split(COMMA)
    }

    /**
     * @description : 카드를 한장 더 받을지 혹은 거절할지를 결정한다.
     */
    fun recommandDrawDisplay(playerName: String): Boolean {
        println("${playerName}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        return when(readln()) {
            YES -> true
            NO -> false
            else -> throw IllegalArgumentException("y 또는 n을 입력해주세요.")
        }
    }

}
