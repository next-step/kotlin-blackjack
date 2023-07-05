package blakjack.domain

import blakjack.domain.Participant.ParticipantType.PLAYER

class Player(
    name: String,
) : Participant(name, PLAYER) {
    var bettingMoney: Money = Money.ZERO
        private set
    var result: Result = Result.NONE
        private set

    override fun win(other: Participant) {
        super.win(other)
        result = Result.WIN
    }

    override fun lose() {
        result = Result.LOSE
    }

    override fun profit(): Money {
        return when {
            isBlackjack() -> bettingMoney * (1.5)
            else -> bettingMoney
        }
    }

    fun bet(money: Money) {
        this.bettingMoney = money
    }

    enum class Result {
        WIN, LOSE, NONE
    }
}
