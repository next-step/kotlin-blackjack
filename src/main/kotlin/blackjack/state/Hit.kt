package blackjack.state

internal object Hit : Playing() {
    override fun isHit(): Boolean {
        return true
    }

    override fun isStart(): Boolean {
        return false
    }
}
