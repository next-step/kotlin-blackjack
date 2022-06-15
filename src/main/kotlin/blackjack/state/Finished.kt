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

    override fun hit(): PlayerState {
        throw UnsupportedOperationException("게임을 진행할 수 없는 상태라서 상태를 변경할 수 없습니다.")
    }

    override fun stay(): PlayerState {
        throw UnsupportedOperationException("게임을 진행할 수 없는 상태라서 상태를 변경할 수 없습니다.")
    }

    override fun bust(): PlayerState {
        throw UnsupportedOperationException("게임을 진행할 수 없는 상태라서 상태를 변경할 수 없습니다.")
    }

    override fun blackjack(): PlayerState {
        throw UnsupportedOperationException("게임을 진행할 수 없는 상태라서 상태를 변경할 수 없습니다.")
    }
}
