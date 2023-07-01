package blackjack.domain.result

import blackjack.domain.user.Dealer

data class DealerResult(val dealer: Dealer, val winCount: Int, val drawCount: Int, val loseCount: Int)
