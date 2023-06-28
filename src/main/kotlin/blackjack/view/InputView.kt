package blackjack.view

class InputView {
    fun inputPlayerNames(): List<String> {
        return inputReadLine().split(",")
    }

    fun inputPlayerDrawCard(playerName: String): Boolean {
        println("${playerName}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        return inputReadLine() == "y"
    }

    private fun inputReadLine() = readLine()!!
}
