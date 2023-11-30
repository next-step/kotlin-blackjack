package blackjack.domain

class Dealer(
    override val name: String,
) : Participant(name) {
    override fun receiveCard(card: Card) {
        cards.add(card)
    }

    override fun canReceiveOneMoreCard(): Boolean {
        return CardScoreCalculator.isUnderScore(
            cards,
            CardScoreCalculator.DEALER_SCORE_THRESHOLD
        )
    }

    infix fun versus(players: Players): List<GameResult> {
        return GameResult.resultOfDealer(players, this)
    }
}

