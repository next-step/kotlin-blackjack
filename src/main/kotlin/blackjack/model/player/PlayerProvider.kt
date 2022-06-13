package blackjack.model.player

interface PlayerProvider {
    fun createPlayers(): Players<Player.Guest>

    fun createDealer(): Player.Dealer =
        Player.Dealer("딜러")
}
