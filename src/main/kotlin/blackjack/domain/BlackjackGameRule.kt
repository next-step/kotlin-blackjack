package blackjack.domain

const val START_CARD_COUNT = 2
const val HIT_CARD_COUNT = 1

infix fun Player.matchWith(dealer: Dealer): MatchStatus {
    return when {
        this.isBust() -> MatchStatus.LOSE
        dealer.isBust() -> MatchStatus.WIN
        isBlackjack() && dealer.isBlackjack() -> MatchStatus.PUSH
        isBlackjack() -> MatchStatus.BLACKJACK
        dealer.isBlackjack() -> MatchStatus.LOSE
        score > dealer.score -> MatchStatus.WIN
        score == dealer.score -> MatchStatus.PUSH
        else -> MatchStatus.LOSE
    }
}
