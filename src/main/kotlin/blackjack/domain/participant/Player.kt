package blackjack.domain.participant

import blackjack.domain.deck.Card
import blackjack.domain.participant.state.Init
import blackjack.domain.participant.state.Score

class Player(
    val playerName: PlayerName,
    val firstCard: Card,
    val secondCard: Card,
) : Participant(
    state = Init.receiveCard(firstCard = firstCard, secondCard = secondCard)
) {
    fun isRunning(): Boolean = !state.isFinished()

    fun getPlayerNameValue(): String = playerName.value

    fun judgementGameResult(otherScore: Score): GameResult = state.judgementGameResult(otherScore = otherScore)

    companion object {
        fun of(nameValue: String, firstCard: Card, secondCard: Card): Player = Player(
            playerName = PlayerName(value = nameValue),
            firstCard = firstCard,
            secondCard = secondCard
        )
    }
}
