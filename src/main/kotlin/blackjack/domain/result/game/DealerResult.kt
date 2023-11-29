package blackjack.domain.result.game

import blackjack.domain.Dealer

data class DealerResult(
    val dealer: Dealer,
    val status: VictoryStatues,
) {
    companion object {
        fun of(dealer: Dealer, playerResults: List<PlayerResult>): DealerResult =
            DealerResult(
                dealer,
                playerResults.map { it.status.opponentResult }.let(::VictoryStatues)
            )
    }
}
