package blackjack.model

class DealerResult(override val player: Dealer, val win: Int, val lose: Int) : Result
