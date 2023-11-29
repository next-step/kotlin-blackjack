package blackjack.domain.card

object CardScoreNormalAcePolicy : CardScorePolicy {
    override fun getScore(card: Card): CardScore {
        return CardScore(card.number.score)
    }
}
