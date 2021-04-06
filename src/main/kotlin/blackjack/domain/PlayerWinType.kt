package blackjack.domain

enum class PlayerWinType {
    WIN, LOSE, DRAW, BLACKJACK;

    companion object {
        fun findPlayerWinType(playerPoint: PlayerPoint, dealerPoint: PlayerPoint): PlayerWinType {
            if (playerPoint.isBlackjack && !dealerPoint.isBlackjack) {
                return BLACKJACK
            }

            if (dealerPoint.point > Player.BLACK_JACK_TWENTY_ONE || dealerPoint.point < playerPoint.point) {
                return WIN
            }

            if (dealerPoint == playerPoint) {
                return DRAW
            }

            return LOSE
        }

        fun isLose(winType: PlayerWinType): Boolean = winType == LOSE
        fun isWin(winType: PlayerWinType): Boolean = winType == WIN || winType == BLACKJACK
    }
}
