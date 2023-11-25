package blackjack.model.card

object CardFixture {
    fun makeCards(vararg cardArray: Card): Cards {
        return Cards(cardArray.toList())
    }

    val ace1 = Card.of(Suit.HEART, CardRank.ACE)
    val ace2 = Card.of(Suit.DIAMOND, CardRank.ACE)
    val two = Card.of(Suit.DIAMOND, CardRank.TWO)
    val three = Card.of(Suit.DIAMOND, CardRank.THREE)
    val four = Card.of(Suit.DIAMOND, CardRank.FOUR)
    val five = Card.of(Suit.DIAMOND, CardRank.FIVE)
    val six = Card.of(Suit.DIAMOND, CardRank.SIX)
    val seven = Card.of(Suit.DIAMOND, CardRank.SEVEN)
    val eight = Card.of(Suit.DIAMOND, CardRank.EIGHT)
    val nine = Card.of(Suit.DIAMOND, CardRank.NINE)
    val ten = Card.of(Suit.DIAMOND, CardRank.TEN)
    val jack = Card.of(Suit.CLOVER, CardRank.JACK)
    val queen = Card.of(Suit.CLOVER, CardRank.QUEEN)
    val king = Card.of(Suit.CLOVER, CardRank.KING)
}
