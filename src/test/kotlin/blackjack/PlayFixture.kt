package blackjack

object PlayFixture {
    val TEST_NAME = "오길환"
    val FIRST_PLAYER = Player(TEST_NAME, Card(CardSuite.HEART, CardNumber.KING), Card(CardSuite.SPADE, CardNumber.QUEEN))
    val SECOND_PLAYER = Player(TEST_NAME, Card(CardSuite.HEART, CardNumber.TWO), Card(CardSuite.SPADE, CardNumber.FIVE))

    val PLAYERS = Players(setOf(FIRST_PLAYER, SECOND_PLAYER))
}
