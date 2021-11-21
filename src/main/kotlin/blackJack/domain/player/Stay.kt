package blackJack.domain.player

object Stay : Strategy {
    override fun isContinue(): Boolean {
        return false
    }
}
