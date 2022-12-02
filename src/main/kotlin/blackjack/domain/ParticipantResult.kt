package blackjack.domain

class ParticipantResult(participants: String) {

    var participant: List<Participant>
        private set

    init {
        require(participants.isNotBlank()) { "입력 값이 없습니다." }
        participant = participants.split(",")
            .map { it.trim() }
            .map { Participant(it) }
    }
}
