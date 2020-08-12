package blackjack.domain

class Dealer(
    cards: Cards = Cards.empty(),
    state: State = getStateFrom(cards)
) : Player(NAME, cards, state) {

    override fun hit(deck: DrawStrategy): Player {
        if (getScore() >= DEALER_STOP_HIT_BOUNDARY_SCORE) {
            val newCards = cards + deck.fetchCard()
            return this.copy(cards = newCards, state = getStateFrom(newCards))
        }
        return this
    }

    override fun copy(name: String, cards: Cards, state: State): Player {
        return Dealer(cards, state)
    }

    companion object {
        private const val NAME = "딜러"
        private const val DEALER_STOP_HIT_BOUNDARY_SCORE = 17
    }
}

class Challenger(
    name: String,
    cards: Cards = Cards.empty(),
    state: State = getStateFrom(cards)
) : Player(name, cards, state) {

    override fun hit(deck: DrawStrategy): Player {
        validatePlayerCanHit()
        val newCards = this.cards + deck.fetchCard()
        return this.copy(cards = newCards, state = getStateFrom(cards))
    }

    private fun validatePlayerCanHit() {
        require(this.state !is State.Busted) { "총점(${cards.sumScores()})이 21점을 초과해 카드를 더 가져올 수 없습니다. 카드목록 : $cards" }
        require(this.state !is State.Stand) { "Stand 상태에서는 카드를 더 뽑을 수 없습니다." }
    }

    override fun copy(name: String, cards: Cards, state: State): Player {
        return Challenger(name, cards, state)
    }
}
