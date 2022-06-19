package blackjack.domain

data class Participants(
    val dealer: Participant.Dealer,
    val players: List<Participant.Player>
) {
    val all: List<Participant> by lazy {
        mutableListOf<Participant>(dealer).apply {
            addAll(players)
        }
    }
}
