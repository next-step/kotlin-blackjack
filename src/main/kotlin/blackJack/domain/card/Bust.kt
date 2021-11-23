package blackJack.domain.card

object Bust : Signal {
    override fun isContinue(): Boolean {
        return false
    }
}
