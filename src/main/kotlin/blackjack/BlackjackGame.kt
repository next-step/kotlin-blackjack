package blackjack

class BlackjackGame {
    fun init(participants: List<Participant>){
        participants.forEach { participant ->
            repeat(2) {
                participant.addCard()
            }
        }
    }

    fun makeParticipants(names: List<String>): List<Participant> {
        return names.map { Participant(it) }
    }
}