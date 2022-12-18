package domain

enum class WinStatus {

    WIN,
    LOSE,
    TIE
    ;

    companion object {

        fun valueOf(winner :GameParticipator?) : WinStatus{
            if (winner == null){
                return TIE
            }
            if (winner is Player){
                return WIN
            }
            return LOSE
        }

    }
}
