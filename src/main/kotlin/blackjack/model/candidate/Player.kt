package blackjack.model.candidate

import blackjack.model.card.Cards

class Player(
    playerName: CandidateName,
    cards: Cards = Cards(),
    private val amount: BettingAmount
) : Candidate(candidateName = playerName, cards = cards) {

    val bettingAmount
        get() = amount.amount

    companion object {
        fun from(name: String, bettingAmount: Int): Player =
            Player(playerName = CandidateName(name), amount = BettingAmount(bettingAmount))
    }
}
