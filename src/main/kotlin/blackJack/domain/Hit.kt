package blackJack.domain

class Hit : DecisionStatus {
    override fun isContinue(): Boolean {
        return true
    }
}
