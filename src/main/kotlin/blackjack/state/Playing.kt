package blackjack.state

internal sealed class Playing : PlayerState {
    override fun isPlaying(): Boolean {
        return true
    }

    override fun isFinished(): Boolean {
        return false
    }

    override fun isStay(): Boolean {
        return false
    }

    override fun isBust(): Boolean {
        return false
    }

    override fun isBlackjack(): Boolean {
        return false
    }
}
