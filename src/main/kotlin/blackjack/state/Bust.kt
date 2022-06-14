package blackjack.state

internal object Bust : Finished() {
    override fun isBust(): Boolean {
        return true
    }

    override fun isStay(): Boolean {
        return false
    }

    override fun isBlackjack(): Boolean {
        return false
    }
}
