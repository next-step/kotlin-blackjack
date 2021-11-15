package blackJack.domain

class Hit : PlayerDecision {
    override fun isContinue(): Boolean {
        return true
    }
}
