package blackjack.domain

data class PlayerResult(val player: Player, val result: ResultStatus, val profit: Profit = Profit(0)) {
    companion object {
        fun from(player: User, dealer: Dealer): PlayerResult {
            val playerResult = dealer.getMatchResult(player)
            return PlayerResult(player, playerResult)
        }
    }
}
