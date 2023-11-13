package blackjack.model

import blackjack.dto.Card
import blackjack.dto.Deck

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

    companion object {
        const val DEALER_NAME = "딜러"
        const val DEALER_HIT_POINT = 16
    }
}
