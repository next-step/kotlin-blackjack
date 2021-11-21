package blackJack.domain.player

object Hit : Strategy {
    override fun isContinue(): Boolean {
        return true
    }
}
