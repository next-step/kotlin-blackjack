package blackjack.view

object InputView {

    private const val PLAYER_DELIMITER = ","
    private const val POSITIVE_ANSWER = "y"
    private const val NEGATIVE_ANSWER = "n"

    fun playerNames(): Collection<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        return readlnOrNull()
            ?.split(PLAYER_DELIMITER).also { println() }
            ?: throw IllegalArgumentException("플레이어 이름을 입력해주세요.")
    }

    fun isWantedMorePredicate(name: String): Boolean {
        println("${name}는 한장의 카드를 더 받겠습니까?(예는 $POSITIVE_ANSWER, 아니오는 $NEGATIVE_ANSWER)")
        return when (readlnOrNull()) {
            POSITIVE_ANSWER -> true
            NEGATIVE_ANSWER -> false
            else -> throw IllegalArgumentException("$POSITIVE_ANSWER 또는 $NEGATIVE_ANSWER 중 하나를 입력해주세요.")
        }
    }
}
