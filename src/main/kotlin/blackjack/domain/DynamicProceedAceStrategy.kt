package blackjack.domain

import blackjack.domain.card.CardNumber

class DynamicProceedAceStrategy : ProceedAceStrategy {
    override fun proceedAceNumber(sum: Int): Int {
        if (sum + CardNumber.ACE_MAXINUM <= RuleChecker.CONDITION_TO_WIN_BLACK_JACK) {
            return CardNumber.ACE_MAXINUM
        }
        return CardNumber.ACE_MINIMUM
    }
}