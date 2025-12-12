package view

fun String?.ifNullEmpty(): String {
    return this ?: ""
}

class InputView {
    companion object {
        fun inputPlayerNames(): List<String> {
            return readln().ifNullEmpty().split(",").map { it -> it.trim() }
        }
    }
}
