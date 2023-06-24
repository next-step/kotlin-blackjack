package blackjack.vo

import blackjack.domain.Cards
import blackjack.domain.Participant

class ParticipantVO(
    val name: String,
    val cards: List<CardVO>,
) {
    companion object {
        fun of(participant: Participant): ParticipantVO {
            return ParticipantVO(
                participant.name,
                participant.cards.map {
                    CardVO(
                        DenominationVO.of(it.denomination),
                        SuitVO.of(it.suit),
                    )
                }
            )
        }

        fun of(name: String, cards: Cards): ParticipantVO {
            return ParticipantVO(
                name,
                cards.map {
                    CardVO(
                        DenominationVO.of(it.denomination),
                        SuitVO.of(it.suit),
                    )
                }
            )
        }
    }
}
