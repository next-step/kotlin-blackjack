package view

fun String?.ifNullEmpty(): String {
    return this ?: ""
}

class InputView {
    companion object {
        fun inputPlayerNames(): List<String> {
            try {
                return readln().ifNullEmpty().split(",").map { it -> it.trim() }
            } catch (e: Exception) {
                println("잘못된 입력입니다. 다시 입력해주세요.")
                inputPlayerNames()
            }
            return emptyList()
        }

        fun inputPlayerBettingAmounts(): Long {
            try {
                return readln().ifNullEmpty().trim().toLong()
            } catch (e: Exception) {
                println("잘못된 입력입니다. 다시 입력해주세요.")
                inputPlayerBettingAmounts()
            }
            return 0L
        }

        fun inputIsNo(): Boolean {
            val input = readln().ifNullEmpty().trim().lowercase()
            return input == "n"
        }
    }
}
