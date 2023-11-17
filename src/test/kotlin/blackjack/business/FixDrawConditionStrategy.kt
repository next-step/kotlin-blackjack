package blackjack.business

class FixDrawConditionStrategy : DrawConditionStrategy {
    override fun shouldDraw(playerName: String): Boolean {
        return true
    }
}
