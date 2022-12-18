package blackjack.domain

data class PlayerResult(val player: Player, val result: ResultStatus) {
    companion object {
        fun from(player: Player, dealer: Dealer): PlayerResult {
            val result = dealer.getMatchResult(player)
            return PlayerResult(player, result)
        }
    }
}
