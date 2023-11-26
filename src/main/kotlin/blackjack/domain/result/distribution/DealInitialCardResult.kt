package blackjack.domain.result.distribution

import blackjack.domain.Dealer
import blackjack.domain.player.Players
import blackjack.domain.result.Result

data class DealInitialCardResult(
    val dealer: Dealer,
    val players: Players,
) : Result()
