package blackjack.domain

import blackjack.domain.card.CardNumber

class StaticProceedAceStrategy : ProceedAceStrategy {
    override fun proceedAceNumber(sum: Int): Int {
        return CardNumber.A.value
    }
}
