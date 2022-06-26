package blackjack.view

enum class MoreCardMark(
    val mark: String,
    private val needMoreCard: Boolean,
) {
    YES("y", true),
    NO("n", false);

    companion object {
        fun needMoreCard(mark: String) =
            values().firstOrNull { it.mark == mark }
                ?.needMoreCard
                ?: throw IllegalArgumentException("존재하지 않는 표시입니다. (mark: $mark)")
    }
}
