package blackjack.domain.participant

import blackjack.domain.Score
import blackjack.domain.card.CardDeck
import blackjack.domain.participant.vo.CardsInHand
import blackjack.domain.participant.vo.Name
import blackjack.domain.participant.vo.ParticipantInformation
import blackjack.domain.participant.vo.WinningScore
import blackjack.domain.participant.vo.WinningScores

private const val READY_CARD_COUNT = 2

abstract class Participant(
    participantInformation: ParticipantInformation,
    cardsInHand: CardsInHand
) {
    var participantInformation: ParticipantInformation
        protected set

    val cardsInHand: CardsInHand = cardsInHand

    var winningScores = WinningScores(emptyList())
        protected set

    val name: Name
        get() = participantInformation.name

    val score: Score
        get() = cardsInHand.calculateScore()

    init {
        this.participantInformation = participantInformation
    }

    open fun ready(cardDeck: CardDeck): Unit = repeat(READY_CARD_COUNT) {
        cardsInHand.add(cardDeck.draw())
        changeStatus()
    }

    fun hit(cardDeck: CardDeck) {
        validateHitRules()

        cardsInHand.add(cardDeck.draw())

        changeStatus()
    }

    protected abstract fun validateHitRules()

    protected open fun changeStatus() {
        participantInformation = participantInformation.changeStatus(score)
    }

    fun score(participants: List<Participant>): Unit = participants.map {
        when {
            participantInformation.isBust() -> WinningScore.LOSE
            it.participantInformation.isBust() -> WinningScore.WIN
            else -> WinningScore.valueOf(score.compareTo(it.score))
        }
    }.let { winningScores = WinningScores(it) }
}
