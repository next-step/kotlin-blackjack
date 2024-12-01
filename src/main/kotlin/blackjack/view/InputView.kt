package blackjack.view

class InputView {
    fun printMessage(message: String) {
        println(message)
    }

    fun readInput(): String {
        return readln()
    }

    fun askForHitOrStand(playerName: String): Boolean {
        printMessage("${playerName}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        while (true) {
            val input = readln().trim().lowercase()
            when (input) {
                "y" -> return true
                "n" -> return false
                else -> println("잘못된 입력입니다. 예는 y, 아니오는 n으로 입력해주세요.")
            }
        }
    }
}
