package blackjack

class Player(name: String) : Gamer(name) {
    override val canGetCard: Boolean
        get() = !isBusted

    infix fun vs(dealer: Dealer): MatchResult {
        val dealerCards = dealer.playerCards

        if (isBusted) return MatchResult.LOSE
        if (dealerCards.isBusted()) return MatchResult.WIN

        if (isBlackjack) {
            return if (dealerCards.isBlackjack()) MatchResult.DRAW
            else MatchResult.WIN
        }
        if (dealerCards.isBlackjack()) return MatchResult.LOSE

        return if (dealerCards.getBestScore() > playerCards.getBestScore()) MatchResult.LOSE
        else if (dealerCards.getBestScore() < playerCards.getBestScore()) MatchResult.WIN
        else MatchResult.DRAW
    }
}
