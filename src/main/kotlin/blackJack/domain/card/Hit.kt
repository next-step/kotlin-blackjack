package blackJack.domain.card

object Hit : Signal {
    override fun isContinue(): Boolean {
        return true
    }
}
