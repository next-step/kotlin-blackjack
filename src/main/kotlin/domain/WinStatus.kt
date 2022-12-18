package domain

enum class WinStatus {

    WIN,
    LOSE,
    TIE;

    companion object {

        fun valueOf(winner: GameParticipator?): WinStatus {
            return when (winner) {
                null -> TIE
                is Player -> WIN
                is Dealer -> LOSE
            }
        }
    }
}
