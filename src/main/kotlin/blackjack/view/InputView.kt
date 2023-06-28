package blackjack.view

class InputView {
    fun inputPlayerNames(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        return inputReadLine().split(",")
    }

    fun inputPlayerDrawCard(playerName: String): Boolean {
        println("${playerName}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        return inputReadLine() == "y"
    }

    private fun inputReadLine() = readLine()!!
}
