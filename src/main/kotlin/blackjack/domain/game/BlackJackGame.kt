package blackjack.domain.game

import blackjack.domain.player.PlayerGroup

class BlackJackGame(
    private val playerGroup: PlayerGroup,
    private val cardDealer: CardDealer,
) {
    fun dealCard(count: Int): PlayerGroup {
        return PlayerGroup(
            playerGroup.players.map {
                it.receiveCard(cardDealer.selectCard(count))
            }
        )
    }

    fun start(): PlayerGroup {
        return dealCard(START_CARD_COUNT)
    }

    companion object {
        private const val START_CARD_COUNT = 2
    }
}
