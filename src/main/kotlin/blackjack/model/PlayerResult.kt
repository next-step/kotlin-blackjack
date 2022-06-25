package blackjack.model

class PlayerResult(override val player: Player, val win: Boolean) : Result
