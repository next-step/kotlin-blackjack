package blackjack.application

import blackjack.domain.Deck
import java.math.BigDecimal

data class CreateGameCommand(
    val names: List<String>,
    val bets: List<BigDecimal>,
    val deck: Deck,
) {
    init {
        require(names.size == bets.size) {
            "이름들과 베팅들의 숫자가 일치해야 합니다."
        }
    }
}
