package blackjack.view

fun tokenizeUserNames(input: String): List<String> {
    return input
        .split(",")
        .filterNot { it.isBlank() }
        .map { it.trim() }
}

fun inputToBoolean(input: String): Boolean {
    return when (input) {
        "y", "Y" -> true
        "n", "N" -> false
        else -> throw IllegalArgumentException("y 또는 n만 입력 가능합니다.")
    }
}
