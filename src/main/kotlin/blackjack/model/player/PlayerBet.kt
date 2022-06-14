package blackjack.model.player

data class PlayerBet(val player: Player.Guest, val betMoney: Int = MIN_BET_MONEY) {

    companion object {
        const val MIN_BET_MONEY = 10000
    }
}

data class PlayerBets(val playerBetList: List<PlayerBet>) : List<PlayerBet> by playerBetList {

    companion object {

        fun List<PlayerBet>.toPlayerBets(): PlayerBets = PlayerBets(this)
    }
}
