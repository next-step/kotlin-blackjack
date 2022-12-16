package blackjack.domain

enum class WinOrLose(
    val description: String,
) {
    WIN("승"), LOSE("패");

    companion object {
        fun of(winOrLose: Boolean): WinOrLose {
            return when (winOrLose) {
                true -> WIN
                false -> LOSE
            }
        }
    }
}
