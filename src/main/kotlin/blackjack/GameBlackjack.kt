package blackjack

interface GameBlackjack {

    fun initialDealing(playerNames: String): GamePlayers
    fun continueDealing(player: GamePlayer): GamePlayer

    companion object {
        const val GAME_INIT_CARD_SIZE = 2
        const val BLACKJACK_MAX_SCORE = 21
        const val PLAYER_NAME_DELIMITER = ","
    }
}
