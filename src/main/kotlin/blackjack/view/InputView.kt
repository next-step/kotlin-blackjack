package blackjack.view

object InputView {
    private const val DELIMITER = ","
    private const val Y = "y"
    private const val N = "n"

    fun inputPlayerNames(): List<String> = input("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        .split(DELIMITER)
        .map { it.trim() }

    fun checkDraw(name: String): Boolean = input("${name}는 한장의 카드를 더 받겠습니까?(예는 $Y, 아니오는 $N)")
        .also {
            require(it == Y || it == N) {
                "[InputView] $Y 또는 ${N}을 입력해주세요."
            }
        } == Y

    private fun input(message: String): String {
        println(message)
        return readln()
    }
}
