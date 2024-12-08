package blackjack

class Bank(accounts: List<ParticipantAccount2> = emptyList()) {
    private val accounts: MutableList<ParticipantAccount2> = accounts.toMutableList()

    fun bet(
        participant: Participant,
        betAmount: Double = 0.0,
    ) {
        if (participant is Player) {
            require(Money(betAmount) > Money.ZERO) {
                "플레이어는 1원 이상 베팅해야 합니다 betAmount: $betAmount"
            }
        }
        accounts.add(
            ParticipantAccount2(
                participant = participant,
                initialBalance = Money(betAmount),
            ),
        )
    }

    fun balance(participant: Participant): Double {
        return accounts.find { it.participant == participant }?.currentBalance?.toDouble() ?: 0.0
    }

    fun settleBets(gameResults: List<GameResult2>) = gameResults.forEach(::settleBet)

    fun settleBet(gameResult: GameResult2) {
        val dealerAccount = findDealerAccount()
        val playerAccount =
            findAccount(gameResult.player)
                ?: throw IllegalStateException("플레이어 계좌를 찾지 못했습니다: player=${gameResult.player}")

        settleOutCome(gameResult.outcome, dealerAccount, playerAccount)
    }

    fun profits(): List<ParticipantProfit> {
        return accounts.map {
            ParticipantProfit(
                it.participant,
                it.profit.toDouble(),
            )
        }
    }

    private fun settleOutCome(
        outcome: Outcome,
        dealerAccount: ParticipantAccount2,
        playerAccount: ParticipantAccount2,
    ) {
        when (outcome) {
            Outcome.WIN -> settleWin(dealerAccount, playerAccount)
            Outcome.LOSS -> settleLoss(dealerAccount, playerAccount)
            Outcome.BLACKJACK -> settleBlackJack(dealerAccount, playerAccount)
            Outcome.PUSH -> Unit
        }
    }

    private fun settleWin(
        dealerAccount: ParticipantAccount2,
        playerAccount: ParticipantAccount2,
    ) {
        val betAmount = playerAccount.currentBalance
        updateBalances(dealerAccount, -betAmount, playerAccount, betAmount)
    }

    private fun settleLoss(
        dealerAccount: ParticipantAccount2,
        playerAccount: ParticipantAccount2,
    ) {
        val betAmount = playerAccount.currentBalance
        updateBalances(dealerAccount, betAmount, playerAccount, -betAmount)
    }

    private fun settleBlackJack(
        dealerAccount: ParticipantAccount2,
        playerAccount: ParticipantAccount2,
    ) {
        val betAmount = playerAccount.currentBalance * BLACKJACK_BET_MULTIPLIER
        updateBalances(dealerAccount, -betAmount, playerAccount, betAmount)
    }

    private fun updateBalances(
        dealerAccount: ParticipantAccount2,
        dealerAdjustment: Money,
        playerAccount: ParticipantAccount2,
        playerAdjustment: Money,
    ) {
        accounts.updateAccount(dealerAccount, dealerAdjustment)
        accounts.updateAccount(playerAccount, playerAdjustment)
    }

    private fun MutableList<ParticipantAccount2>.updateAccount(
        account: ParticipantAccount2,
        newBalance: Money,
    ) {
        val index = indexOf(account)
        if (index != -1) {
            this[index] = account.updateBalance(newBalance)
        }
    }

    private fun findDealerAccount(): ParticipantAccount2 {
        return accounts.find { it.participant is Dealer }
            ?: throw IllegalStateException("딜러 계좌가 존재하지 않습니다")
    }

    private fun findAccount(participant: Participant): ParticipantAccount2? {
        return accounts.find { it.participant == participant }
    }

    companion object {
        private const val BLACKJACK_BET_MULTIPLIER = 1.5
    }
}
