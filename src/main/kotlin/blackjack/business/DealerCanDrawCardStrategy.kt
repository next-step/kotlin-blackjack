package blackjack.business

class DealerCanDrawCardStrategy : CanDrawCardStrategy {
    override fun canDrawCard(sum: Int): Boolean {
        return sum < 17
    }
}
