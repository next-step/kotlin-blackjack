package blackjack.domain.model

enum class YesNo(val value: String) {
    Y("y"), N("n");

    companion object {
        fun from(value: String): YesNo? {
            return values().find { it.value == value }
        }
    }
}
