package blackjack

typealias CompareScore = (GameParticipant, GameParticipant) -> Boolean

enum class MatchResult(
    val message: String,
    private val compare: CompareScore
) {
    WIN("승", { player, other -> player > other }),
    LOSS("패", { player, other -> player < other })
    ;

    companion object {
        fun of(player: GameParticipant, other: GameParticipant) =
            MatchResult.values().find { it.compare(player, other) }
                ?: throw RuntimeException()
    }
}
