package blackjack.domain.game

import blackjack.domain.player.PlayerGroup

class BlackJackGame {
    fun dealCard(cardDealer: CardDealer, playerGroup: PlayerGroup, count: Int): PlayerGroup {
        return PlayerGroup(
            playerGroup.players.map {
                it.receiveCard(cardDealer.selectCard(count))
            }
        )
    }

    companion object {
        const val START_CARD_COUNT = 2
    }
}
