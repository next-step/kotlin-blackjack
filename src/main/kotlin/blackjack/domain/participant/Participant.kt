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
    participantInformation: ParticipantInformation
) {
    var participantInformation: ParticipantInformation
        protected set

    val cardsInHand: CardsInHand

    var winningScores: WinningScores

    val name: Name
        get() = participantInformation.name

    val score: Score
        get() = cardsInHand.calculateScore()

    init {
        this.participantInformation = participantInformation
        this.cardsInHand = CardsInHand()
        this.winningScores = WinningScores()
    }

    open fun ready(cardDeck: CardDeck) = repeat(READY_CARD_COUNT) { cardsInHand.add(cardDeck.draw()) }

    fun hit(cardDeck: CardDeck) {
        validateHitRules()

        cardsInHand.add(cardDeck.draw())

        changeStatus()
    }

    protected abstract fun validateHitRules()

    protected open fun changeStatus() {
        participantInformation = participantInformation.changeStatus(score)
    }

    fun score(participants: List<Participant>) = participants.filterNot { it === this }
        .forEach {
            winningScores = when {
                participantInformation.isBust() -> winningScores.add(WinningScore.LOSE)
                it.participantInformation.isBust() -> winningScores.add(WinningScore.WIN)
                else -> winningScores.add(WinningScore.valueOf(score.compareTo(it.score)))
            }
        }
}
