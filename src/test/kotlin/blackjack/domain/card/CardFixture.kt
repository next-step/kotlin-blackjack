package blackjack.domain.card

object CardFixture {

    val heartAce: Card = heartCard(CardDenomination.ACE)
    val heartTwo: Card = heartCard(CardDenomination.TWO)
    val heartThree: Card = heartCard(CardDenomination.THREE)
    val heartFour: Card = heartCard(CardDenomination.FOUR)
    val heartFive: Card = heartCard(CardDenomination.FIVE)
    val heartSeven: Card = heartCard(CardDenomination.SEVEN)
    val heartTen: Card = heartCard(CardDenomination.TEN)
    val heartJack: Card = heartCard(CardDenomination.JACK)
    val heartQueen: Card = heartCard(CardDenomination.QUEEN)
    val heartKing: Card = heartCard(CardDenomination.KING)
    val spadeAce: Card = spadeCard(CardDenomination.ACE)
    val spadeFour: Card = spadeCard(CardDenomination.FOUR)
    val spadeTen: Card = spadeCard(CardDenomination.TEN)
    val spadeQueen: Card = spadeCard(CardDenomination.QUEEN)
    val spadeKing: Card = spadeCard(CardDenomination.KING)

    private fun heartCard(denomination: CardDenomination): Card {
        return Card(
            shape = CardShape.HEART,
            denomination = denomination,
        )
    }

    private fun spadeCard(denomination: CardDenomination): Card {
        return Card(
            shape = CardShape.SPADE,
            denomination = denomination,
        )
    }
}
