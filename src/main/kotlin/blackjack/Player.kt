package blackjack

class Player(name: String) : Gamer(name) {
    override val canGetCard: Boolean
        get() = !isBusted

    override fun getInitialPublicCards(playerCards: PlayerCards): PlayerCards = playerCards

    infix fun vs(dealer: Dealer): MatchResult {
        val dealerCards = dealer.playerCards

        if (isBusted) return MatchResult.LOSE
        if (dealerCards.isBusted()) return MatchResult.WIN

        if (isBlackjack) {
            return if (dealerCards.isBlackjack()) MatchResult.DRAW
            else MatchResult.WIN
        }
        if (dealerCards.isBlackjack()) return MatchResult.LOSE

        return when {
            dealerCards.getBestScore() > playerCards.getBestScore() -> MatchResult.LOSE
            dealerCards.getBestScore() < playerCards.getBestScore() -> MatchResult.WIN
            else -> MatchResult.DRAW
        }
    }
}
