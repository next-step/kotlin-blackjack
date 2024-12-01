package blackjack.view

object InputView {
    private const val DELIMITER = ","

    fun inputPlayerNames(): List<String> = input("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        .split(DELIMITER)
        .map { it.trim() }

    fun checkDraw(name: String) = input("${name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        .also {
            require(it == "y" || it == "n") {
                "[InputView] y 또는 n을 입력해주세요."
            }
        }

    private fun input(message: String): String {
        println(message)
        return readln()
    }
}
