package blackjack.domain

enum class PlayerWinLoseResult(val odds: Double) {
    WIN(1.0),
    LOSE(-1.0),
    PUSH(0.0),
    BLACKJACK(1.5), ;

    companion object {
        fun compareResult(
            dealer: Dealer,
            player: Player,
        ): PlayerWinLoseResult {
            return when {
                dealer.isBlackJack() -> LOSE
                dealer.isBust() -> WIN
                player.isBust() -> LOSE
                player.isBlackJack() && !dealer.isBlackJack() -> BLACKJACK
                dealer.getCardSum() > player.hand.calculateCardsMaxSum() -> LOSE
                dealer.getCardSum() == player.hand.calculateCardsMaxSum() -> PUSH
                else -> WIN
            }
        }
    }
}
