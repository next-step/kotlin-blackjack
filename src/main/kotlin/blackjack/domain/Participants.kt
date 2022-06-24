package blackjack.domain

class Participants(
    val dealer: Dealer = Dealer(),
    player: List<Participant> = emptyList(),
) {
    private val _participants = player.toMutableList().apply { add(0, dealer) }
    val participants: List<Participant> get() = _participants.toList()
    val player: List<Participant> get() = _participants.filterIsInstance<Player>()

    fun namesAsText() = participants.joinToString { it.name }
}
