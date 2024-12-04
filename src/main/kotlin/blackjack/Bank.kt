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
}
