package blackJack.domain

import blackJack.utils.StringUtils
import java.util.NoSuchElementException

class GamePlayers(private val players: List<GamePlayer>) {

    fun toList(): List<GamePlayer> = players

    fun getDealer(): GamePlayer = players.firstOrNull { it.isDealer() } ?: throw NoSuchElementException(NOT_FOUND_DEALER)

    fun getPlayers(): GamePlayers = GamePlayers(players.filter { !it.isDealer() })

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
        private const val NOT_FOUND_DEALER = "딜러가 없습니다."
    }
}
