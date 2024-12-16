package blackjack.domain

enum class PlayerWinLoseResult {
    WIN,
    LOSE,
    PUSH, ;

    companion object {
        fun compareResult(
            dealer: Dealer,
            player: Player,
        ): PlayerWinLoseResult {
            return when {
                dealer.isBlackJack() -> LOSE
                dealer.isBust() -> WIN
                player.isBust() -> LOSE
                player.isBlackJack() && !dealer.isBlackJack() -> WIN
                dealer.getCardSum() > player.hand.calculateCardsMaxSum() -> LOSE
                dealer.getCardSum() == player.hand.calculateCardsMaxSum() -> PUSH
                else -> WIN
            }
        }
    }
}
