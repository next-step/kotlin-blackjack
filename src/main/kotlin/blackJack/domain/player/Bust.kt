package blackJack.domain.player

object Bust : Strategy {
    override fun isContinue(): Boolean {
        return false
    }
}
