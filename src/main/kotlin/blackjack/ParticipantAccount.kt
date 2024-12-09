package blackjack

data class ParticipantAccount(
    val participant: Participant,
    private val balance: Balance = Balance(),
) {
    val currentBalance get() = balance.current
    val profit get() = balance.profit()

    constructor(participant: Participant, initialBalance: Money) : this(
        participant,
        Balance(
            initial = initialBalance,
            current = initialBalance,
        ),
    )

    fun updateBalance(amount: Money): ParticipantAccount {
        return this.copy(balance = balance.update(amount))
    }
}

data class Balance(
    private val initial: Money = Money.ZERO,
    val current: Money = Money.ZERO,
) {
    fun profit(): Money = current - initial

    fun update(amount: Money): Balance {
        return this.copy(current = current + amount)
    }
}
