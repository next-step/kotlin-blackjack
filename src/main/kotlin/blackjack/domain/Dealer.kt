package blackjack.domain

class Dealer(
    override val name: String,
) : Participant(name) {
    override fun receiveCard(card: Card) {
        cards.add(card)
    }

    fun needOneMoreCard(): Boolean {
        return cards.toScore() <= Score.DEALER_HIT_THRESHOLD
    }

    infix fun versus(players: Players): List<GameResult> {
        return GameResult.resultOfDealer(players, this)
    }
}

