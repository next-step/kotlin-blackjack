package blackJack.domain.card

object Stay : Signal {
    override fun isContinue(): Boolean {
        return false
    }
}
