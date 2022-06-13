package blackjack.domain

import blackjack.domain.dto.GameParticipationRequest

class Dealer(private val deck: Deck) : Player("딜러") {

    val shouldDraw: Boolean
        get() = score.sum <= DRAW_THRESHOLD

    fun giveCard(player: Player) {
        player.addCard(deck.draw())
    }

    fun prepareGame(requests: List<GameParticipationRequest>): List<Player> {
        repeat(INIT_CARD_COUNT) { drawSelf() }

        return requests.map {
            Player(it.name, Hand(emptyList(), it.bet))
                .also { player -> repeat(INIT_CARD_COUNT) { giveCard(player) } }
        }
    }

    fun drawSelf() {
        giveCard(this)
    }

    companion object {
        private const val INIT_CARD_COUNT = 2
        const val DRAW_THRESHOLD = 16
    }
}
