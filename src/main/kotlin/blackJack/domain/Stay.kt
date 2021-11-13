package blackJack.domain

class Stay : DecisionStatus {
    override fun isContinue(): Boolean {
        return false
    }
}
