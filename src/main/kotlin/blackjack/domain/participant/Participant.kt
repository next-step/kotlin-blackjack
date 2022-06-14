package blackjack.domain.participant

import blackjack.domain.Score
import blackjack.domain.card.CardDeck
import blackjack.domain.participant.vo.Amount
import blackjack.domain.participant.vo.CardsInHand
import blackjack.domain.participant.vo.Name
import blackjack.domain.participant.vo.ParticipantInformation
import blackjack.domain.participant.vo.WinningAmount

private const val READY_CARD_COUNT = 2

abstract class Participant(
    participantInformation: ParticipantInformation,
    val cardsInHand: CardsInHand
) {
    var participantInformation: ParticipantInformation
        protected set

    var winningAmount: WinningAmount = WinningAmount(Amount(0))

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

    fun <T : Participant> score(scoreStrategy: ScoreStrategy<T>) {
        scoreStrategy.compare(this as T)
    }
}
