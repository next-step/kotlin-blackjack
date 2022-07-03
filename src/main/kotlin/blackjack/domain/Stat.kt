package blackjack.domain

enum class Stat(val value: String) {
    WIN(value = "승"),
    DRAW(value = "무"),
    LOSE(value = "패")
    ;

    fun opposite(): Stat {
        return when (this) {
            WIN -> LOSE
            DRAW -> DRAW
            LOSE -> WIN
        }
    }
}
