package blackjack.state

internal object Hit : Playing() {
    override fun isHit(): Boolean {
        return true
    }

    override fun isStart(): Boolean {
        return false
    }

    override fun hit(): PlayerState {
        return Hit
    }

    override fun stay(): PlayerState {
        return Stay
    }

    override fun bust(): PlayerState {
        return Bust
    }

    override fun blackjack(): PlayerState {
        return Blackjack
    }
}
