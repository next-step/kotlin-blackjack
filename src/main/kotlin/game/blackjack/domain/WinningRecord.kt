package game.blackjack.domain

enum class WinningRecord(val scale: Int) {
    WIN(1),
    LOSE(-1),
    TIE(0),
    ;

    companion object {
        private const val BLACK_JACK_RATE = 1.5
        fun getPlayerProfit(player: Player, dealer: Dealer): Int = when {
            player.isBust() -> LOSE.scale * player.money
            dealer.isBust() -> WIN.scale * player.money
            player.isBlackJack() && dealer.isBlackJack() -> TIE.scale * player.money
            player.isBlackJack() -> (WIN.scale * BLACK_JACK_RATE * player.money).toInt()
            dealer.isBlackJack() -> LOSE.scale * player.money
            player.score() > dealer.score() -> WIN.scale * player.money
            player.score() < dealer.score() -> LOSE.scale * player.money
            else -> TIE.scale * player.money
        }
    }
}
