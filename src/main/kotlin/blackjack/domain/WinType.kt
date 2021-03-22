package blackjack.domain

enum class WinType {
    WIN, LOSE, DRAW;

    companion object {
        fun findWinTypeOfPlayer(playerPoint: Int, dealerPoint: Int): WinType {
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
