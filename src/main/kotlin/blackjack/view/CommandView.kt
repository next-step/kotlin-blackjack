package blackjack.view

enum class CommandView(
    val view: String,
) {
    YES("y"),
    NO("n"),
    ;

    companion object {
        fun from(view: String) = values().firstOrNull { it.view == view }
    }
}
