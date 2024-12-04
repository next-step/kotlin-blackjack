package blackjack

class Bank(val accounts: MutableMap<Participant, Long> = mutableMapOf()) {
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
}
