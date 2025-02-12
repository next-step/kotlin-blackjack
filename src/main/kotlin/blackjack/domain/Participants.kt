package blackjack.domain

class Participants(private val participants: List<Participant>): List<Participant> by participants {

    constructor(vararg participantNames: String): this(participantNames.map { name -> Participant(name.trim()) })

    fun receiveFirstTurnCard(blackjackShoe: BlackjackShoe) {
        participants.forEach { participant -> participant.receiveFirstTurnCard(blackjackShoe) }
    }
}
