package blackjack.view

object InputView {

    fun inputPlayerName(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        return readln().split(",").map { it.trim() }
    }

    fun checkAddCard(playerName: String): Boolean {
        println("${playerName}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        val value = readln()
        runCatching { require(value == "y" || value == "n") }
            .onFailure {
                println("y 또는 n을 입력해주세요.")
                checkAddCard(playerName)
            }
        return yesOrNo(value)
    }

    private fun yesOrNo(input: String): Boolean {
        return input == "y"
    }
}
