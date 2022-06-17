package blackjack.domain.participant.player

import blackjack.domain.Amount
import blackjack.domain.Score
import blackjack.domain.participant.Participant
import blackjack.domain.participant.type.Status
import blackjack.domain.participant.vo.BetAmount
import blackjack.domain.participant.vo.CardsInHand
import blackjack.domain.participant.vo.Name
import blackjack.domain.participant.vo.ParticipantInformation

private const val BLACKJACK_PRICE = 1.5

class Player(
    participantInformation: ParticipantInformation,
    cardsInHand: CardsInHand,
    val betAmount: BetAmount
) : Participant(participantInformation, cardsInHand) {
    val isBlackJack: Boolean
        get() = cardsInHand.isBlackJack

    override fun validateHitRules() {
        require(!participantInformation.isStay()) { "Stay를 선언하였다면 카드를 뽑을수 없습니다." }
        require(score < Score.BLACKJACK) {
            "현재 점수가 ${Score.BLACKJACK.value} 보다 크거나 같으면 카드를 뽑지 못합니다."
        }
    }

    fun winBetAmount(): Amount = if (isBlackJack) {
        BetAmount(Amount((betAmount.amount.value * BLACKJACK_PRICE).toInt())).amount
    } else {
        betAmount.amount
    }

    fun stay() {
        participantInformation = participantInformation.stay()
    }

    companion object {
        fun sit(name: Name, betAmount: BetAmount): Player =
            Player(ParticipantInformation(name, Status.PLAY), CardsInHand(), betAmount)
    }
}
