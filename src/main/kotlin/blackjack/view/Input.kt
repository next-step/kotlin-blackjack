package blackjack.view

import blackjack.domain.player.Participant

interface Input {
    fun askParticipantNames(): List<String>
    infix fun askDrawMoreCard(participant: Participant): String
}
