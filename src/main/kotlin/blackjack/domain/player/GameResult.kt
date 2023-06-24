package blackjack.domain.player

enum class GameResult(val korName: String) {
    WIN("승"), LOOSE("패"), TIE("무"),
    ;

    companion object {
        fun valueOfOpposition(gameResult: GameResult): GameResult {
            return when (gameResult) {
                WIN -> LOOSE
                LOOSE -> WIN
                TIE -> TIE
            }
        }
    }
}
