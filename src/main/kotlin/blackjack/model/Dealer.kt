package blackjack.model

import blackjack.dto.Card
import blackjack.dto.Deck
import blackjack.model.Point.Companion.WINNING_POINT

class Dealer : Player(DEALER_NAME) {

    private val cardSet = Deck.newDeck()
    private var pointer = 0

    fun dealingTwoCards(): List<Card> {
        require(pointer + 2 < cardSet.size) { "카드가 부족합니다." }
        return cardSet
            .subList(pointer, pointer + 2)
            .apply { pointer += 2 }
    }

    fun dealingOneCard(): Card {
        require(pointer + 1 < cardSet.size) { "카드가 부족합니다." }
        return cardSet[pointer++]
    }

    fun initialCardDealing() {
        addCards(dealingTwoCards())
    }

    fun moreCard(): Boolean {
        var hitted = false
        if (hit && getPoints() <= DEALER_HIT_POINT) {
            addCard(dealingOneCard())
            hitted = true
        }
        noMoreHit()

        return hitted
    }

    fun compareWithPlayers(players: Players) {
        val winAndLose = players.values
            .map { compareWithPlayer(it) }
            .groupBy { it }

        makeResult(
            winAndLose[true]?.size ?: 0,
            winAndLose[false]?.size ?: 0
        )
    }

    private fun compareWithPlayer(player: Player): Boolean? {
        val dealerPoint = getPoints()
        val playerPoint = player.getPoints()

        player.compare(this)
        return if (dealerPoint == playerPoint || (dealerPoint > WINNING_POINT && playerPoint > WINNING_POINT)) {
            null
        } else if (dealerPoint > WINNING_POINT || (playerPoint in (dealerPoint + 1)..WINNING_POINT)) {
            false
        } else {
            true
        }
    }

    companion object {
        private const val DEALER_NAME = "딜러"
        private const val DEALER_HIT_POINT = 16
    }
}
