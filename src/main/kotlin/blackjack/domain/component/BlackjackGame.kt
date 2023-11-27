package blackjack.domain.component

import blackjack.domain.model.*

class BlackjackGame private constructor(
    private val players: Players,
    private val cardPool: CardPool
) {
    fun isHitPossible(playerName: PlayerName): Boolean {
        return players
            .findByName(playerName)
            .checkNull()
            .isPossibleToHit()
    }

    fun hit(playerName: PlayerName): Player {
        val player = players.findByName(playerName)
        val card = cardPool.pickAndRemove()

        return player
            .checkNull()
            .append(card)
    }

    private fun Player?.checkNull(): Player {
        check(this != null) { "해당 플레이어는 존재하지 않습니다." }

        return this
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
