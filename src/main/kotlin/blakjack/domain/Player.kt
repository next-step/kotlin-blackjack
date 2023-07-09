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

    override fun lose(other: Participant) {
        result = Result.LOSE
    }

    override fun blackjack() {
        super.blackjack()
        result = Result.BLACKJACK
    }

    override fun profit(): Money {
        return result.profit(bettingMoney)
    }

    fun bet(money: Money) {
        this.bettingMoney = money
    }

    enum class Result(
        val profitRate: Double
    ) {
        BLACKJACK(1.5),
        WIN(1.0),
        LOSE(-1.0),
        DRAW(0.0),
        ;

        fun profit(money: Money): Money {
            return money * profitRate
        }
    }
}
