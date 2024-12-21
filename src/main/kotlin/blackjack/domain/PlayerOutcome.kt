package blackjack.domain

enum class PlayerOutcome {
    WIN,
    LOSE,
    DRAW,
    ;

    companion object {
        fun of(
            player: Player,
            dealer: Dealer,
        ): PlayerOutcome =
            when {
                player.isBusted -> LOSE
                dealer.isBusted -> WIN
                player.isBlackjack && !dealer.isBlackjack -> WIN
                player.pushes(dealer) -> DRAW
                player.beats(dealer) -> WIN
                else -> LOSE
            }
    }
}
