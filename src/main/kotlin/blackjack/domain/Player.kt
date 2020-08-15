package blackjack.domain

abstract class Player(
    val name: String,
    val cards: Cards,
    val state: State
) {
    init {
        validateStateWithScore()
    }

    private fun validateStateWithScore() {
        if (getScore() > Cards.BLACK_JACK_SCORE && state !is State.Busted) {
            throw IllegalArgumentException("총점이 21점을 넘으면 상태 값이 Busted 이어야 합니다. 총점 : ${getScore()}입력 : $state")
        }
    }

    operator fun plus(players: List<Player>): List<Player> = listOf(this) + players

    fun isBiggerScoreThan(other: Player): Boolean {
        return this.getScore() > other.getScore()
    }

    fun getScore(): Int {
        return cards.sumScores()
    }

    fun stand(): Player {
        return this.copy(state = State.Stand)
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

    abstract fun copy(
        name: String = this.name,
        cards: Cards = this.cards,
        state: State = this.state
    ): Player

    abstract fun getStateFrom(cards: Cards): State

    companion object {
        @JvmStatic
        protected fun getPlayerStateFrom(cards: Cards): State {
            if (cards.sumScores() > Cards.BLACK_JACK_SCORE) {
                return State.Busted
            }
            if (cards.isBlackJack()) {
                return State.BlackJack
            }
            return State.Playing
        }
    }
}

// getStateFrom 을 class 내부로 옮겨서  abstract로 하려니 nonFinal Method Calling 경고가 뜨네요..
// 이 점수에 따른 상태값을 뱉는 메서드를 오버라이드 하는게 맞다고 생각하는데 어렵네요..
// 클래스 내부로 선언하면 생성자에서 사용하지 못해서 static에 올렸습니다.
// 생성자에서 바로 state를 계산하고싶은데 더 좋은 방법을 못찾겠습니다 ㅠㅠ
