package blackjack.model.player

interface PlayerProvider {
    fun createGuestPlayers(): Players<Player.Guest>

    fun betForPlayer(player: Player.Guest): Int = PlayerBet.MIN_BET_MONEY

    fun createDealer(): Player.Dealer =
        Player.Dealer("딜러")
}
