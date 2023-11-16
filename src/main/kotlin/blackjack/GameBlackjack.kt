package blackjack

object GameBlackjack {

    private const val GAME_INIT_CARD_SIZE = 2

    fun init(gamePlayers: GamePlayers): GamePlayers =
        gamePlayers.players.map {
            it.copy(cards = GameDealer.deal(GAME_INIT_CARD_SIZE))
        }.let { GamePlayers(it) }
}
