package blackjack.state

internal object Start : Playing() {

    override fun isHit(): Boolean {
        return false
    }

    override fun isStart(): Boolean {
        return true
    }
}
