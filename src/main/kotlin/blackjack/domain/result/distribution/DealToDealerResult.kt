package blackjack.domain.result.distribution

import blackjack.domain.result.Result

data class DealToDealerResult(
    val isHit: Boolean,
) : Result()
