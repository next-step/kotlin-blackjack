package blakjack.domain

import blakjack.domain.Participant.ParticipantType.PLAYER

class Player(
    name: String,
) : Participant(name, PLAYER) {
    var bettingMoney: Money = Money.ZERO
        private set
    var result: Result = Result.DRAW
        private set

    override fun win(other: Participant) {
        super.win(other)
        result = Result.WIN
    }

    override fun draw() {
        result = Result.DRAW
    }

    override fun lose() {
        result = Result.LOSE
    }

    override fun profit(): Money {
        return when {
            isBlackjack() -> bettingMoney * (1.5)
            isDraw() -> Money.ZERO
            isLose() -> bettingMoney.negate()
            else -> bettingMoney
        }
    }

    fun bet(money: Money) {
        this.bettingMoney = money
    }

    private fun isDraw(): Boolean {
        return result == Result.DRAW
    }

    private fun isLose(): Boolean {
        return result == Result.LOSE
    }

    enum class Result {
        WIN, LOSE, DRAW
    }
}
