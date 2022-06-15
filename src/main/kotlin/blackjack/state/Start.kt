package blackjack.state

internal object Start : Playing() {

    override fun isHit(): Boolean {
        return false
    }

    override fun isStart(): Boolean {
        return true
    }

    override fun hit(): PlayerState {
        return Hit
    }

    override fun stay(): PlayerState {
        return Stay
    }

    override fun bust(): PlayerState {
        throw UnsupportedOperationException("게임을 이제 막 시작한 상태에서는 bust 상태가 될 수 없습니다.")
    }

    override fun blackjack(): PlayerState {
        return Blackjack
    }
}
