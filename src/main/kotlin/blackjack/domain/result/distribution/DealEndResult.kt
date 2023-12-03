package blackjack.domain.result.distribution

import blackjack.domain.Dealer
import blackjack.domain.player.Players
import blackjack.domain.result.Result

data class DealEndResult(
    val players: Players,
    val dealer: Dealer,
) : Result()
