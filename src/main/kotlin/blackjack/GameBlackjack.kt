package blackjack

object GameBlackjack {

    private const val GAME_INIT_CARD_SIZE = 2
    const val BLACKJACK_MAX_SCORE = 21
    const val PLAYER_NAME_DELIMITER = ","

    fun initialDealing(playerNames: String): GamePlayers {
        val gamePlayers = playerNames.split(PLAYER_NAME_DELIMITER)
            .map {
                GamePlayer(
                    name = it,
                    cards = GameDealer.deal(GAME_INIT_CARD_SIZE),
                    action = PlayerAction.INIT
                )
            }
        return GamePlayers(gamePlayers)
    }

    fun continueDealing(player: GamePlayer): GamePlayer =
        player.receiveCard(GameDealer.deal())
}
