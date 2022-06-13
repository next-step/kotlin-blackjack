package blackjack.model.player

interface PlayerProvider {
    fun createPlayers(previousPlayers: Players<Player.Guest>? = null): Players<Player.Guest>

    fun createDealer(): Player.Dealer =
        Player.Dealer("딜러")
}
