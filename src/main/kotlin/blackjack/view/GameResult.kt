package blackjack.view

enum class GameResult(val result: Boolean, val view: String) {
    WIN(true, "승"),
    LOSE(false, "패")
}
