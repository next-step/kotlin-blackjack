package view

fun String?.ifNullEmpty(): String {
    return this ?: ""
}

class InputView {
    companion object {
        fun inputPlayerNames(): List<String> {
            println("게임에 참여할 사람의 이름을 입력하세요.")
            return readln().ifNullEmpty().split(",").map { it.trim() }
        }

        fun inputPlayerMoney(playerName: String): Int {
            println("$playerName 의 배팅 금액은?")
            return readln().ifNullEmpty().toInt()
        }

        fun inputIsContinue(): Boolean {
            val input = readln().ifNullEmpty().trim().lowercase()
            return input == "y"
        }
    }
}
