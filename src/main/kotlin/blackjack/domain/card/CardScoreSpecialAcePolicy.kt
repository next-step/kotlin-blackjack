package blackjack.domain.card

object CardScoreSpecialAcePolicy : CardScorePolicy {
    private const val SPECIAL_SCORE = 11

    override fun getScore(card: Card): CardScore {
        if (card.isAce()) return CardScore(SPECIAL_SCORE)
        return CardScore(card.number.score)
    }
}
