package blackjack.state

internal object Stay : Finished() {
    override fun isBust(): Boolean {
        return false
    }

    override fun isStay(): Boolean {
        return true
    }

    override fun isBlackjack(): Boolean {
        return false
    }
}
