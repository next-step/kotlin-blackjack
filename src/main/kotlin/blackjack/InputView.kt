package blackjack

fun oneMoreCardInput(): Boolean {
    val input = readlnOrNull() ?: throw IllegalArgumentException("올바른 입력을 해주세요")
    return when (input) {
        "y" -> true
        "n" -> false
        else -> throw IllegalArgumentException("올바른 입력을 해주세요")
    }
}

fun namesInput(): List<String> {
    return readlnOrNull()?.split(",") ?: throw IllegalArgumentException("이름을 입력해주세요.")
}