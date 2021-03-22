package blackjack.domain

enum class PlayerWinType {
    WIN, LOSE, DRAW;

    companion object {
        fun findPlayerWinType(playerPoint: Int, dealerPoint: Int): PlayerWinType {
            if (dealerPoint > Player.BLACK_JACK_TWENTY_ONE || dealerPoint < playerPoint) {
                return WIN
            }

            if (dealerPoint == playerPoint) {
                return DRAW
            }

            return LOSE
        }
    }
}
