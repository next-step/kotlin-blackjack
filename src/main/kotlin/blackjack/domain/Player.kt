package blackjack.domain

class Player(name: String) : Participant(name) {
    fun match(dealer: Dealer): RecordType {
        val playerScore = totalScore()
        val dealerScore = dealer.totalScore()

        if (playerScore > BLACKJACK_SCORE) return RecordType.LOSE
        if (dealerScore > BLACKJACK_SCORE) return RecordType.WIN

        if (playerScore > dealerScore) return RecordType.WIN
        if (playerScore < dealerScore) return RecordType.LOSE

        return compareCardCount(dealer)
    }

    private fun compareCardCount(dealer: Dealer): RecordType {
        return when {
            cards.size < dealer.cards.size -> RecordType.WIN
            cards.size > dealer.cards.size -> RecordType.LOSE
            else -> RecordType.DRAW
        }
    }
}
