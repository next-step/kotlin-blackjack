package blackjack.domain.participant

import blackjack.domain.deck.Card
import blackjack.domain.participant.state.Init
import blackjack.domain.participant.state.State

class Player(val playerName: PlayerName) {

    private lateinit var state: State

    fun receiveInitCards(firstCard: Card, secondCard: Card) {
        this.state = Init.receiveCard(firstCard = firstCard, secondCard = secondCard)
    }

    fun receiveCard(card: Card) {
        this.state = this.state.receiveCard(card = card)
    }

    fun stay() {
        this.state = this.state.stay()
    }

    fun isRunning(): Boolean = !state.isFinished()

    fun cards(): List<Card> {
        return try {
            this.state.cards()
        } catch (_: UninitializedPropertyAccessException) {
            throw IllegalStateException("아직 초기 카드를 받지 못했습니다.")
        }
    }

    fun getPlayerNameValue(): String = playerName.value

    fun getScore(): Int = this.state.score()

    companion object {
        fun from(nameValue: String): Player = Player(
            playerName = PlayerName(value = nameValue)
        )
    }
}
