package blackjack.view

object InputView {
    private const val DELIMITER = ","
    private val RESPONSE_MAP = mapOf("y" to true, "n" to false)

    fun inputNames(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        return readln().split(DELIMITER)
    }

    fun inputHitOrStand(name: String): Boolean {
        println("${name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        return RESPONSE_MAP[readln()] ?: throw IllegalArgumentException("잘못된 입력입니다.")
    }
}
