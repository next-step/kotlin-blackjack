package blackJack.domain

class Bust : DecisionStatus {
    override fun isContinue(): Boolean {
        return false
    }
}
