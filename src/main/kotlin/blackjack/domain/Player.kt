package blackjack.domain

data class Player(
    val name: String,
    val cards: Cards = Cards.empty(),
    val state: State = getStateFrom(cards)
) {
    fun deal(deck: DrawStrategy): Player {
        return this.copy(cards = this.cards + deck.getDealCards())
    }

    fun stand(): Player {
        return this.copy(state = State.Stand)
    }

    fun hit(deck: DrawStrategy): Player {
        validatePlayerCanHit()
        val newCards = this.cards + deck.fetchCard()
        return this.copy(cards = newCards, state = getStateFrom(newCards))
    }

    private fun validatePlayerCanHit() {
        require(this.state !is State.Busted) { "총점(${cards.sumScores()})이 21점을 초과해 카드를 더 가져올 수 없습니다. 카드목록 : $cards" }
        require(this.state !is State.Stand) { "Stand 상태에서는 카드를 더 뽑을 수 없습니다." }
    }

    fun getScore(): Int {
        return cards.sumScores()
    }

    companion object {
        private fun getStateFrom(cards: Cards): State {
            if (cards.sumScores() > Cards.BLACK_JACK_SCORE) {
                return State.Busted
            }
            return State.Playing
        }
    }
}
