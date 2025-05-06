enum class GameResult {
    WIN,
    LOSE,
    DRAW,
    ;

    companion object {
        fun getApposite(result: GameResult): GameResult {
            return when (result) {
                WIN -> LOSE
                DRAW -> DRAW
                LOSE -> WIN
            }
        }
    }
}
