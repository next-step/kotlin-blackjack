package blackjack

class Bank(accounts: Map<Participant, Long> = emptyMap()) {
    private val accounts: MutableMap<Participant, Long> = accounts.toMutableMap()

    fun bet(
        participant: Participant,
        betAmount: Long = 0,
    ) {
        if (participant is Player) {
            require(betAmount > 0) {
                "플레이어는 1원 이상 베팅해야 합니다 betAmount: $betAmount"
            }
        }
        accounts[participant] = betAmount
    }

    fun balance(participant: Participant): Long {
        return accounts[participant] ?: 0
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
            else -> TODO()
        }
    }
}
