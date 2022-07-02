package blackjack.domain

const val START_CARD_COUNT = 2
const val HIT_CARD_COUNT = 1

infix fun Player.vs(dealer: Dealer): MatchStatus {
    return when {
        isBlackjack() && dealer.isBlackjack() -> MatchStatus.PUSH
        isBlackjack() -> MatchStatus.BLACKJACK
        dealer.isBlackjack() -> MatchStatus.LOSE
        isBust() -> MatchStatus.LOSE
        dealer.isBust() -> MatchStatus.WIN
        score > dealer.score -> MatchStatus.WIN
        score == dealer.score -> MatchStatus.PUSH
        else -> MatchStatus.LOSE
    }
}

infix fun Player.getRevenueFrom(dealer: Dealer): Revenue {
    return revenue by (this vs dealer)
}

private val Player.revenue: BetAmount
    get() = betAmount

private infix fun BetAmount.by(matchStatus: MatchStatus): Revenue {
    return Revenue((value * matchStatus.revenueRatio).toInt())
}

fun List<BlackjackParticipantResult>.getDealerRevenue(): Revenue {
    require(all { result -> result.participant is Player }) {
        "플레이어의 결과 수익으로만 딜러의 수익을 계산할 수 있습니다."
    }

    val playersRevenue = sumOf { result ->
        result.revenue.value
    }
    return Revenue(playersRevenue).reverse()
}
