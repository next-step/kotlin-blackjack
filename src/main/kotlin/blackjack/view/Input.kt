package blackjack.view

import blackjack.domain.player.Participant
import blackjack.domain.player.vo.Name

interface Input {
    fun askParticipantNames(): List<Name>
    infix fun askDrawMoreCard(participant: Participant): String
}
