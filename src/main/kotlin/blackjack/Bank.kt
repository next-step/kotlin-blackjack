package blackjack

class Bank(val accounts: MutableMap<Participant, Long> = mutableMapOf()) {
    fun bet(
        participant: Participant,
        betAmount: Long = 0,
    ) {
        accounts[participant] = betAmount
    }
}
