package blackJack.domain

import blackJack.utils.StringUtils

class GamePlayers(private val players: List<GamePlayer>) {

    fun toList(): List<GamePlayer> = players

    fun startBlackJack(playingCard: PlayingCard): GamePlayers {
        val players = players.map { player ->
            factorial(INIT_RECEIVE_CARD_NUMBER, player, playingCard)
        }
        return GamePlayers(players)
    }

    private tailrec fun factorial(n: Int, gamePlayer: GamePlayer, playingCard: PlayingCard): GamePlayer {
        return if (n <= 0) {
            gamePlayer
        } else {
            gamePlayer.receiveCard(playingCard.drawCard())
            factorial(n - 1, gamePlayer, playingCard)
        }
    }

    companion object {
        fun enterGameRoom(names: String): GamePlayers {
            val players = StringUtils.splitString(names).map { Player.of(it) }
            val dealer = Dealer()
            return GamePlayers(players + dealer)
        }

        private const val INIT_RECEIVE_CARD_NUMBER = 2
    }
}
