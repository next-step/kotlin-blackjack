package blackjack.business.canDrawCardStrategy

class DealerCanDrawCardStrategy : CanDrawCardStrategy {
    override fun canDrawCard(sum: Int): Boolean {
        return sum < 17
    }
}
