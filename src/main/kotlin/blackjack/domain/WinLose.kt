package blackjack.domain

enum class WinLose {
    WIN, LOSE, DRAW
    ;

    fun opposite(): WinLose {
        return when (this) {
            WIN -> LOSE
            LOSE -> WIN
            DRAW -> DRAW
        }
    }
}
