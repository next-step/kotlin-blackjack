package blackjack.state

internal object Blackjack : Finished() {
    override fun isBust(): Boolean {
        return false
    }

    override fun isStay(): Boolean {
        return false
    }

    override fun isBlackjack(): Boolean {
        return true
    }
}
