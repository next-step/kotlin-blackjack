package blackJack.domain

import blackJack.utils.StringUtils

class Players(private val players: List<Player>) {

    fun toList(): List<Player> = players

    fun startBlackJack(playingCard: PlayingCard): Players {
        val players = players.map { player ->
            factorial(INIT_RECEIVE_CARD_NUMBER, player, playingCard)
        }
        return Players(players)
    }

    private tailrec fun factorial(n: Int, player: Player, playingCard: PlayingCard): Player {
        return if (n <= 0) {
            player
        } else {
            player.receiveCard(playingCard.drawCard())
            factorial(n - 1, player, playingCard)
        }
    }

    companion object {
        fun enterGameRoom(names: String): Players {
            return Players(StringUtils.splitString(names).map { Player.of(it) })
        }

        private const val INIT_RECEIVE_CARD_NUMBER = 2
    }
}
