package blackjack.domain.component

import blackjack.domain.model.*

class BlackjackGame private constructor(
    private val players: Players,
    private val cardPool: CardPool
) {
    fun isHitPossible(playerName: PlayerName): Boolean {
        val player = players.findByName(playerName)

        check(player != null) { "해당 플레이어는 존재하지 않습니다." }

        return player.isPossibleToHit()
    }

    companion object {
        private const val INITIAL_CARD_COUNT = 2

        fun create(playerNames: List<PlayerName>): BlackjackGame {
            val cardPool = CardPool.create()
            val players = playerNames
                .map { Player(it, fetchInitialCards(cardPool)) }
                .run { Players(this) }

            return BlackjackGame(players, cardPool)
        }

        private fun fetchInitialCards(cardPool: CardPool): Cards {
            return cardPool
                .pickAndRemove(INITIAL_CARD_COUNT)
                .run { Cards.of(this) }
        }
    }
}
