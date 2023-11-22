package blackjack.domain.result

import blackjack.domain.Dealer
import blackjack.domain.player.Players

data class DealInitialCardResult(
    val dealer: Dealer,
    val players: Players
) : Result()
