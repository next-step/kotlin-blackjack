package blackjack.domain.card

object CardScoreNormalAcePolicy : CardScorePolicy {
    override fun getScore(card: Card): CardScore {
        return CardScore(
            when (card.number) {
                CardNumber.ACE -> 1
                CardNumber.TWO -> 2
                CardNumber.THREE -> 3
                CardNumber.FOUR -> 4
                CardNumber.FIVE -> 5
                CardNumber.SIX -> 6
                CardNumber.SEVEN -> 7
                CardNumber.EIGHT -> 8
                CardNumber.NINE -> 9
                CardNumber.TEN -> 10
                CardNumber.JACK -> 10
                CardNumber.QUEEN -> 10
                CardNumber.KING -> 10
            }
        )
    }
}
