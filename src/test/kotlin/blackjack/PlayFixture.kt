package blackjack

object PlayFixture {
    val FIRST_PLAYER = Player(Card(CardSuite.HEART, CardNumber.KING), Card(CardSuite.SPADE, CardNumber.QUEEN))
    val SECOND_PLAYER = Player(Card(CardSuite.HEART, CardNumber.TWO), Card(CardSuite.SPADE, CardNumber.FIVE))

    val PLAYERS = Players(setOf(FIRST_PLAYER, SECOND_PLAYER))
}
