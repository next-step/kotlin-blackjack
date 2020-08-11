package blackjack.domain

class Player(
    val name: String,
    cards: Cards = Cards.empty()
) {
    var cards: Cards = cards
        private set

    var state: State = State.Playing
        private set

    init {
        checkPlayerBusted()
    }

    fun deal(deck: DrawStrategy) {
        repeat(DEAL_DRAW_COUNT) { cards = cards.add(deck.fetchCard()) }
    }

    fun stand() {
        this.state = State.Stand
    }

    fun hit(deck: DrawStrategy) {
        validatePlayerCanHit()
        cards = cards.add(deck.fetchCard())
        checkPlayerBusted()
    }

    private fun validatePlayerCanHit() {
        require(this.state !is State.Busted) { "총점(${cards.sumScores()})이 21점을 초과해 카드를 더 가져올 수 없습니다. 카드목록 : $cards" }
        require(this.state !is State.Stand) { "Stand 상태에서는 카드를 더 뽑을 수 없습니다." }
    }

    private fun checkPlayerBusted() {
        if (cards.sumScores() > Cards.BLACK_JACK_SCORE) {
            state = State.Busted
        }
    }

    fun getScore(): Int {
        return cards.sumScores()
    }

    companion object {
        private const val DEAL_DRAW_COUNT = 2
    }
}
