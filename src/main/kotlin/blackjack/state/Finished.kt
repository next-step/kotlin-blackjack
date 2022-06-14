package blackjack.state

internal sealed class Finished : PlayerState {
    override fun isPlaying(): Boolean {
        return false
    }

    override fun isFinished(): Boolean {
        return true
    }

    override fun isHit(): Boolean {
        return false
    }

    override fun isStart(): Boolean {
        return false
    }
}
