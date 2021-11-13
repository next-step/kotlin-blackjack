package blackJack.domain

class BlackJack : DecisionStatus {
    override fun isContinue(): Boolean {
        return false
    }
}
