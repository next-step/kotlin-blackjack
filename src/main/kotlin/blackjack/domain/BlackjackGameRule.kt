package blackjack.domain

const val START_CARD_COUNT = 2
const val HIT_CARD_COUNT = 1

infix fun Participant.Player.matchWith(dealer: Participant.Dealer): MatchStatus {
    return when {
        this.isBust() -> MatchStatus.Lose
        dealer.isBust() -> MatchStatus.Win
        isBlackjack() && dealer.isBlackjack() -> MatchStatus.Push
        isBlackjack() -> MatchStatus.Win
        dealer.isBlackjack() -> MatchStatus.Lose
        score > dealer.score -> MatchStatus.Win
        score == dealer.score -> MatchStatus.Push
        else -> MatchStatus.Lose
    }
}
