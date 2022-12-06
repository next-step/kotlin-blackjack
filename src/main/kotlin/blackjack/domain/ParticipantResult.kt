package blackjack.domain

class ParticipantResult(participants: String) {

    var players: List<Player>
        private set

    init {
        require(participants.isNotBlank()) { "입력 값이 없습니다." }
        players = participants.split(",")
            .map { it.trim() }
            .map { Player(it) }
    }
}
