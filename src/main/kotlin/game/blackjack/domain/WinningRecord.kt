package game.blackjack.domain

enum class WinningRecord(val scale: Int) {
    WIN(1), LOSE(-1), TIE(0)
}
