package blackjack.model.status

enum class Finish(val str: String) {
    WIN("승"),
    DRAW("무"),
    DEFEAT("패");

    fun reverse() =
        when (this) {
            WIN -> DEFEAT
            DEFEAT -> WIN
            else -> DRAW
        }

    override fun toString() = str
}
