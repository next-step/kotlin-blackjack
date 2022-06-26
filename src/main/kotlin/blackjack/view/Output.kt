package blackjack.view

import blackjack.domain.player.Participant
import blackjack.domain.player.Participants

interface Output {
    infix fun printInitialStatus(participants: Participants)
    infix fun printStatus(participant: Participant)
    infix fun printResult(participants: Participants)
}
