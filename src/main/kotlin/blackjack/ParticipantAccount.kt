package blackjack

data class ParticipantAccount(
    val participant: Participant,
    private val balance: Balance = Balance(),
) {
    val currentBalance get() = balance.current
    val profit get() = balance.profit()

    constructor(participant: Participant, initialBalance: Double) : this(
        participant,
        Balance(
            initial = initialBalance,
            current = initialBalance,
        ),
    )

    fun updateBalance(amount: Double): ParticipantAccount {
        return this.copy(balance = balance.update(amount))
    }
}

data class Balance(
    private val initial: Double = 0.0,
    val current: Double = 0.0,
) {
    fun profit(): Double = current - initial

    fun update(amount: Double): Balance {
        return this.copy(current = current + amount)
    }
}
