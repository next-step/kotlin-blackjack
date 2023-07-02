package blackjack.view

import blackjack.domain.gamestate.Competition
import blackjack.domain.gamestate.Competition.DRAW
import blackjack.domain.gamestate.Competition.LOSE
import blackjack.domain.gamestate.Competition.WIN

fun parseCompetitionView(competition: Competition): String = CompetitionView.from(competition).view

fun parseCompetitionView(competitions: Map<Competition, Int>): String =
    competitions.entries.joinToString(" ") { "${it.value}${parseCompetitionView(it.key)}" }

enum class CompetitionView(
    val competition: Competition,
    val view: String,
) {
    WIN_VIEW(WIN, "승"),
    LOSE_VIEW(LOSE, "패"),
    DRAW_VIEW(DRAW, "무");

    companion object {
        fun from(competition: Competition) = values().find { it.competition == competition }
            ?: throw IllegalArgumentException("뷰에서 지원하지 않는 승부값이다.")
    }
}
