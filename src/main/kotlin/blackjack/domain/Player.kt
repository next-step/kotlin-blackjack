package blackjack.domain

class Player(val name: String, cards: PlayerCards) {
    var cards: PlayerCards = cards
        private set

    var state: States = States.HIT
        get() {
            return States.valueOf(cards.score, cards.size, field)
        }

    val isPlayingState: Boolean
        get() {
            return state == States.HIT
        }

    fun draw(card: Card) {
        throwExceptionIfIsNotPlayingState()

        cards = cards.addCard(card)
    }

    fun stop() {
        state = States.STAY
    }

    fun throwExceptionIfIsNotPlayingState() {
        if (isPlayingState.not()) {
            throw IllegalStateException("게임이 진행 불가능한 상태입니다. : $state")
        }
    }

    constructor(name: String, firstCard: Card, secondCard: Card) : this(name, PlayerCards(firstCard, secondCard))
}
