package blackjack.domain

import blackjack.model.Bet
import blackjack.model.GameResult
import blackjack.model.PlayerProfit

class GamePlayer(
    override val name: String,
    override val bet: Bet,
    override val play: Play = GamePlay(),
) : Player {

    init {
        require(name.isNotBlank()) { "Player 이름은 공백이 될 수 없습니다. 유효한 Player 명을 입력해주세요." }
    }

    override fun profit(dealer: Dealer): PlayerProfit.Player =
        GameResult.of(dealer, this)
            .let {
                when (it) {
                    GameResult.BLACKJACK, GameResult.WIN -> play.profit(bet.value)
                    GameResult.PUSH -> 0.0
                    GameResult.LOSE -> -bet.value.toDouble()
                }
            }
            .let {
                PlayerProfit.Player(name, it)
            }
}
