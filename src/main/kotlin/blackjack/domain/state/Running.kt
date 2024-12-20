package blackjack.domain.state

abstract class Running : State {
    override fun profit(money: Int): Double {
        throw IllegalArgumentException("게임이 종료되지 않았습니다.")
    }
}
