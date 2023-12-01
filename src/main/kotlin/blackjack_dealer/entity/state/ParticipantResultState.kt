package blackjack_dealer.entity.state

enum class ParticipantResultState(val state: String, val multiplyNumber: Int) {
    WIN("승", 1), LOSE("패", -1), DRAW("무", 0);
}
