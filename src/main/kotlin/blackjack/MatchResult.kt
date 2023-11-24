package blackjack

typealias CompareScore = (GameParticipantPlayer, GameParticipantDealer) -> Boolean

enum class MatchResult(
    val message: String,
    private val compare: CompareScore
) {
    WIN("승", { player, other -> player > other }),
    LOSS("패", { player, other -> player < other })
    ;

    companion object {
        fun of(player: GameParticipantPlayer, dealer: GameParticipantDealer) =
            MatchResult.values().find { it.compare(player, dealer) }
                ?: throw RuntimeException()
    }
}
