package blackjack.domain

interface Player {
    val name: String
    val cards: Cards
    val state: State

    operator fun plus(players: List<Player>): List<Player> = listOf(this) + players

    fun isBiggerScoreThan(other: Player): Boolean {
        return this.getScore() > other.getScore()
    }

    fun getScore(): Int {
        return cards.sumScores()
    }

    fun stand(): Player {
        return copy(state = State.Stand)
    }

    fun deal(deck: Deck): Player {
        return this.copy(cards = this.cards + deck.getDealCards())
    }

    fun hit(deck: Deck): Player {
        validatePlayerCanHit()
        val newCards = this.cards + deck.fetchCard()
        return this.copy(cards = newCards, state = getStateFrom(newCards))
    }

    private fun validatePlayerCanHit() {
        require(this.state !is State.Busted) { "총점(${cards.sumScores()})이 21점을 초과해 카드를 더 가져올 수 없습니다. 카드목록 : $cards" }
        require(this.state !is State.Stand) { "Stand 상태에서는 카드를 더 뽑을 수 없습니다." }
    }

    fun getStateFrom(cards: Cards): State

    fun copy(
        name: String = this.name,
        cards: Cards = this.cards,
        state: State = this.state
    ): Player
}
