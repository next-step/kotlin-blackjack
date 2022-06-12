package blackjack.domain.participant

import blackjack.domain.Score
import blackjack.domain.card.CardDeck
import blackjack.domain.participant.type.Status
import blackjack.domain.participant.vo.Name
import blackjack.domain.participant.vo.ParticipantInformation

class Dealer : Participant(ParticipantInformation(Name.dealer(), Status.PLAY)) {

    val isDealerDrawMoreCard: Boolean
        get() = score <= DEALER_SCORE

    override fun ready(cardDeck: CardDeck) {
        super.ready(cardDeck)

        if (score <= DEALER_SCORE) {
            hit(cardDeck)
        }
    }

    override fun validateHitRules() {
        require(score <= DEALER_SCORE) { "$score ${DEALER_SCORE.value} 초과시 카드를 뽑을수 없습니다." }
    }

    override fun changeStatus() {
        if (score > DEALER_SCORE && score <= Score.BLACKJACK) {
            participantInformation = participantInformation.stay()
            return
        }
        participantInformation = participantInformation.changeStatus(cardsInHand.calculateScore())
    }

    companion object {
        private val DEALER_SCORE = Score.of(16)

        fun sit(): Dealer = Dealer()
    }
}
