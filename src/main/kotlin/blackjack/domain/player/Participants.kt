package blackjack.domain.player

import blackjack.domain.card.Deck
import blackjack.domain.player.vo.Name

private const val MINIMUM_PARTICIPANT_SIZE = 1

class Participants private constructor(val value: List<Participant>): Iterable<Participant> {
    init {
        require(value.isNotEmpty()) { "참여자는 최소 ${MINIMUM_PARTICIPANT_SIZE}이상 이어야 합니다." }
    }

    infix fun draw(deck: Deck) {
        value.forEach { it receive deck.draw()}
    }

    override fun iterator(): Iterator<Participant> {
        return value.iterator()
    }

    companion object {
        infix fun from(names: List<Name>): Participants {
            return Participants(names.map { Participant(it) })
        }
    }
}
