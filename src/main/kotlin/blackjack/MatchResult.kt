package blackjack

typealias CompareScore = (GameParticipantPlayer, GameParticipantDealer) -> Boolean

enum class MatchResult(
    val message: String,
    val rate: Double,
    private val compare: CompareScore
) {
    BLACKJACK("승", 1.5, { player, dealer -> player.isBlackjack() }),
    WIN("승", 1.0, { player, dealer -> player > dealer }),
    LOSS("패", -1.0, { player, dealer -> player < dealer }),
    DRAW("무", 0.0, { player, dealer -> player.compareTo(dealer) == 0 })
    ;

    companion object {
        fun of(player: GameParticipantPlayer, dealer: GameParticipantDealer): MatchResult =
            if (player.isBust) LOSS
            else if (player.isBlackjack()) BLACKJACK
            else if (dealer.isBust) WIN
            else MatchResult.values().find { it.compare(player, dealer) }
                ?: throw RuntimeException()
    }
}
