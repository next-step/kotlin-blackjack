package blackjack.domain

class DealerResult(private val dealer: Dealer, results: List<Result>) {
    val result = results.groupBy { it }.mapValues { it.value.size }

    fun dealerName(): String {
        return dealer.name
    }
}
