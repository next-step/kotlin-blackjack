package blackjack.domain.participant

import blackjack.domain.Score
import blackjack.domain.participant.type.Status
import blackjack.domain.participant.vo.BetAmount
import blackjack.domain.participant.vo.CardsInHand
import blackjack.domain.participant.vo.Name
import blackjack.domain.participant.vo.ParticipantInformation

class Player(
    participantInformation: ParticipantInformation,
    cardsInHand: CardsInHand,
    betAmount: BetAmount
) : Participant(participantInformation, cardsInHand) {

    val betAmount: BetAmount = betAmount

    override fun validateHitRules() {
        require(!participantInformation.isStay()) { "Stay를 선언하였다면 카드를 뽑을수 없습니다." }
        require(score < Score.BLACKJACK) {
            "현재 점수가 ${Score.BLACKJACK.value} 보다 크거나 같으면 카드를 뽑지 못합니다."
        }
    }

    fun stay() {
        participantInformation = participantInformation.stay()
    }

    companion object {
        fun sit(name: Name, betAmount: BetAmount): Player =
            Player(ParticipantInformation(name, Status.PLAY), CardsInHand(), betAmount)
    }
}
