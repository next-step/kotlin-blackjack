package blackjack.view.dto

import blackjack.domain.game.BlackJackResult
import blackjack.domain.game.DealerResult
import blackjack.domain.game.GameResult
import blackjack.domain.game.GamerResult

data class BlackJackResultDto(
    val playerResults: List<PlayerResultDto>,
    val dealerResult: DealerResultDto,
) {
    constructor(result: BlackJackResult): this(
        playerResults = result.playerResult.map(::PlayerResultDto),
        dealerResult = DealerResultDto(result.getDealerStatistics()),
    )
}

data class DealerResultDto(val win: Int, val draw: Int, val lost: Int) {

    constructor(result: DealerResult) : this(
        win = result.win,
        draw = result.draw,
        lost = result.lose,
    )
}

data class PlayerResultDto(val result: String, val name: String) {

    constructor(result: GamerResult) : this(
        result = result.result.toName(),
        name = result.name.value,
    )

    companion object {
        private fun GameResult.Type.toName(): String {
            return when (this) {
                GameResult.Type.WIN -> "승"
                GameResult.Type.DRAW -> "무"
                GameResult.Type.LOSE -> "패"
            }
        }
    }
}

