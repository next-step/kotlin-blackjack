package blackJack.domain.player

object BlackJack : Strategy {
    override fun isContinue(): Boolean {
        return false
    }
}
