package blackjack

interface GameBlackjack {

    fun initialDealing(playerNames: String): GamePlayers
    fun continueDealing(player: GamePlayer): GamePlayer
}
