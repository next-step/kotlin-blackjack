package blackjack.model

class DealerResult(override val player: Player, val win: Int, val lose: Int) : Result
