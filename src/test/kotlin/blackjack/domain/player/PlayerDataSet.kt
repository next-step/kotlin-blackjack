package blackjack.domain.player

import blackjack.domain.card.CardsDataSet

class PlayerDataSet {
    companion object {
        fun testDataWithTwoCards(score: Int): Player {
            require(score >= 2) {
                "score should be greater or equal than 2"
            }

            return Player("username", CardsDataSet.testDataWithTwoCards(score))
        }
    }
}
