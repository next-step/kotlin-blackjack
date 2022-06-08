package blackjack.model.player

interface PlayerProvider {
    fun createPlayers(): Players

    fun createDealer(): Player.Dealer =
        Player.Dealer("딜러")
}
