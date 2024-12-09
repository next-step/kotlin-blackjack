package blackjack.core.player

class Dealer : Player(Name(DEALER_NAME)) {
    fun checkMatch(player: Player): MatchResult {
        return when {
            this.checkBust() -> MatchResult.LOSE
            player.checkBust() -> MatchResult.WIN
            this.point > player.point -> MatchResult.WIN
            this.point < player.point -> MatchResult.LOSE
            else -> MatchResult.DRAW
        }
    }

    companion object {
        private const val DEALER_NAME = "딜러"
    }
}
