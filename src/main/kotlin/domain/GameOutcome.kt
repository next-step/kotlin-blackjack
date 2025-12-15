package domain

enum class GameOutcome {
    WIN,
    LOSE,
    ;

    companion object {
        fun judge(bool: Boolean) = if (bool) WIN else LOSE
        fun isWin(value: GameOutcome) = value == WIN
    }
}