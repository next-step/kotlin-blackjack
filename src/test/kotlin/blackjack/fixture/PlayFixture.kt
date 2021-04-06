package blackjack.fixture

import blackjack.domain.Card
import blackjack.domain.CardNumber
import blackjack.domain.CardSuite
import blackjack.domain.Player

object PlayFixture {
    const val TEST_NAME = "오길환"
    private val FIRST_PLAYER = Player(TEST_NAME, Card(CardSuite.HEART, CardNumber.KING), Card(CardSuite.SPADE, CardNumber.QUEEN))
    private val SECOND_PLAYER = Player(TEST_NAME, Card(CardSuite.HEART, CardNumber.TWO), Card(CardSuite.SPADE, CardNumber.FIVE))
}
