package blackJack.domain

class Stay : PlayerDecision {
    override fun isContinue(): Boolean {
        return false
    }
}
