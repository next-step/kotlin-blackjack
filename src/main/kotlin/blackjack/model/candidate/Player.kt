package blackjack.model.candidate

import blackjack.model.card.Cards

class Player(
    playerName: CandidateName,
    cards: Cards = Cards(),
    private val bettingAmount: BettingAmount
) : Candidate(candidateName = playerName, cards = cards) {

    companion object {
        fun from(name: String, bettingAmount: Int): Player =
            Player(playerName = CandidateName(name), bettingAmount = BettingAmount(bettingAmount))

        fun from(name: String, cards: Cards, bettingAmount: Int): Player = Player(
            playerName = CandidateName(name),
            cards = cards,
            bettingAmount = BettingAmount(bettingAmount)
        )
    }
}
