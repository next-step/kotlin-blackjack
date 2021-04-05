package blackjack

class Player(private var playerCards: PlayerCards) {
    var state: States = States.HIT
        get() {
            return States.valueOf(playerCards.score, field)
        }

    val isPlayingState: Boolean
        get() {
            return state == States.HIT
        }

    fun draw(card: Card) {
        if (state == States.STAY) {
            throw IllegalStateException("STAY이므로 draw 할 수 없습니다.")
        }

        playerCards = playerCards.addCard(card)
    }

    fun stop() {
        state = States.STAY
    }

    fun throwExceptionIfIsNotPlayingState() {
        if(isPlayingState.not()) {
            throw IllegalStateException("게임이 진행 불가능한 상태입니다. : $state")
        }
    }

    constructor(firstCard: Card, secondCard: Card): this(PlayerCards(firstCard, secondCard))
}
