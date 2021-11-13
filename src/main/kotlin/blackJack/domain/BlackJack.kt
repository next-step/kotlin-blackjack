package blackJack.domain

class BlackJack : PlayerDecision {
    override fun isContinue(): Boolean {
        return false
    }
}
