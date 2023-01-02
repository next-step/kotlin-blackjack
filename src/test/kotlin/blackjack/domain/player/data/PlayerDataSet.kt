package blackjack.domain.player.data

import blackjack.domain.card.data.CardsDataSet
import blackjack.domain.player.Player

class PlayerDataSet {
    companion object {
        fun testDataWithTwoCards(): Player =
            Player("username", CardsDataSet.testDataWithTwoCards())

        fun testDataWithTwoCards(score: Int): Player =
            Player("username", CardsDataSet.testDataWithTwoCards(score))
    }
}
