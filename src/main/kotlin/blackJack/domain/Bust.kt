package blackJack.domain

class Bust : PlayerDecision {
    override fun isContinue(): Boolean {
        return false
    }
}
