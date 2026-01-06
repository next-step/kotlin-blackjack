package blackjack.domain

class Player(
    name: String,
) : Participant(name) {
    lateinit var betMoney: BetMoney

    fun profit(dealer: Dealer): Double {
        val result = getResult(dealer)
        return betMoney.money * result.payoutRate
    }

    private fun getResult(dealer: Dealer): RecordType {
        val playerScore = score()
        val dealerScore = dealer.score()

        return when {
            isBlackjack() && dealer.isBlackjack() -> RecordType.DRAW // 둘다 블랙잭인경우 비김
            isBlackjack() -> RecordType.BLACKJACK
            isBust() -> RecordType.LOSE // 딜러 승
            dealer.isBust() -> RecordType.WIN // 플레이어 승
            playerScore > dealerScore -> RecordType.WIN // 플레이어 승
            playerScore < dealerScore -> RecordType.LOSE // 딜러 승
            else -> RecordType.DRAW
        }
    }

    fun placeBet(betMoney: BetMoney) {
        this.betMoney = betMoney
    }
}
