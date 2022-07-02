package blackjack.view.input.converter

import blackjack.domain.BetAmount

object BetAmountConverter : InputConverter<BetAmount> {
    override fun convert(input: String): BetAmount {
        return BetAmount(input.toInt())
    }
}
