package blackjack.domain

object Judgment {
    fun execute(participants: List<Participant>): List<PlayerResult> {
        val players = participants.filterIsInstance<Player>()
        val dealer = participants.filterIsInstance<Dealer>().first()

        return players.map { player ->
            when {
                dealer.isBust() -> PlayerResult(name = player.name, result = JudgmentResult.WIN)
                player.isBust() -> PlayerResult(name = player.name, result = JudgmentResult.LOSE)
                player.isBlackJack() -> PlayerResult(name = player.name, result = JudgmentResult.WIN)
                !player.isBlackJack() && dealer.isBlackJack() -> PlayerResult(name = player.name, result = JudgmentResult.LOSE)
                player.isMaxScore() -> PlayerResult(name = player.name, result = JudgmentResult.WIN)
                !player.isMaxScore() && dealer.isMaxScore() -> PlayerResult(name = player.name, result = JudgmentResult.LOSE)
                player.getScore() > dealer.getScore() -> PlayerResult(name = player.name, result = JudgmentResult.WIN)
                player.getScore() < dealer.getScore() -> PlayerResult(name = player.name, result = JudgmentResult.LOSE)
                else -> throw IllegalStateException("게임 결과를 판별하는 중 에러가 발생했습니다.")
            }
        }
    }
}

data class PlayerResult(
    val name: String,
    val result: JudgmentResult,
)

enum class JudgmentResult(
    val description: String,
) {
    WIN("승"),
    LOSE("패"),
    ;
}
