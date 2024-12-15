package blackjack.view.policy

import blackjack.participant.PlayerName
import blackjack.policy.MoreCardPolicy
import blackjack.view.InputView

object InputMoreCardPolicy : MoreCardPolicy {
    override fun isMoreCard(name: PlayerName): Boolean {
        return InputView.inputMoreCard(name)
    }
}
