package blackjack

class Bank(accounts: Map<Participant, Double> = emptyMap()) {
    private val accounts: MutableMap<Participant, Double> = accounts.toMutableMap()

    fun bet(
        participant: Participant,
        betAmount: Double = 0.0,
    ) {
        if (participant is Player) {
            require(betAmount > 0) {
                "플레이어는 1원 이상 베팅해야 합니다 betAmount: $betAmount"
            }
        }
        accounts[participant] = betAmount
    }

    fun balance(participant: Participant): Double {
        return accounts[participant] ?: 0.0
    }

    fun settleBets(gameResult: GameResult) {
        val dealerAccount =
            accounts.entries.find { it.key is Dealer }?.toPair()
                ?: throw IllegalStateException("Dealer account not found")
        val playerAccount =
            accounts.entries.find { it.key == gameResult.player }?.toPair()
                ?: throw IllegalStateException("Player account not found: player=${gameResult.player}")

        val betAmount = playerAccount.second

        when (gameResult.outcome) {
            Outcome.WIN -> {
                accounts[dealerAccount.first] = accounts[dealerAccount.first]!! - betAmount
                accounts[playerAccount.first] = accounts[playerAccount.first]!! + betAmount
            }
            Outcome.LOSS -> {
                accounts[dealerAccount.first] = accounts[dealerAccount.first]!! + betAmount
                accounts[playerAccount.first] = accounts[playerAccount.first]!! - betAmount
            }
            Outcome.BLACKJACK -> {
                accounts[dealerAccount.first] = accounts[dealerAccount.first]!! - (betAmount * 1.5)
                accounts[playerAccount.first] = accounts[playerAccount.first]!! + (betAmount * 1.5)
            }
            Outcome.PUSH -> Unit
        }
    }
}
