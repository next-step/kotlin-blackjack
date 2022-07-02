package game.blackjack.domain

enum class WinningRecord(val profit: Int) {
    WIN(1), LOSE(-1), TIE(0)
}
