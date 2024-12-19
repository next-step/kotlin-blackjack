package blackjack.domain.state

enum class ResultState(val displayMessage: String) {
    WIN("승"),
    LOSE("패"),
    DRAW("무"),
    ;

    fun not(): ResultState {
        return when (this) {
            WIN -> LOSE
            LOSE -> WIN
            else -> DRAW
        }
    }
}
