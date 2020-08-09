package blackjack.domain

class Player(
    val name: String,
    private val cards: Cards = Cards.empty()
) {
    var isStand = false
        private set

    fun getCards(): List<Card> {
        return cards.get()
    }

    fun stand() {
        isStand = true
    }

    fun isFinished(): Boolean {
        return isStand || isBusted()
    }

    private fun isBusted(): Boolean {
        return cards.sumScores() > Cards.BLACK_JACK_SCORE
    }

    fun deal(deck: DrawStrategy) {
        repeat(DEAL_DRAW_COUNT) { cards.add(deck.fetchCard()) }
    }

    fun hit(deck: DrawStrategy) {
        require(!this.isBusted()) { "21점을 초과해 카드를 더 가져올 수 없습니다." }
        cards.add(deck.fetchCard())
    }

    fun getScore(): Int {
        return cards.sumScores()
    }

    companion object {
        private const val DEAL_DRAW_COUNT = 2
    }
}
