package blackjack.ui.dto

enum class Continue(val value: String) {
    TRUE("y"),
    FALSE("n");

    companion object {
        fun of(value: String): Continue =
            values().firstOrNull { it.value == value } ?: throw IllegalArgumentException("y 또는 n만 입력 가능합니다. 입력값 : $value")
    }
}
