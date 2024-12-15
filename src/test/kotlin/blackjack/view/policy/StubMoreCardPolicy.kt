package blackjack.view.policy

import blackjack.participant.PlayerName
import blackjack.policy.MoreCardPolicy

class StubMoreCardPolicy : MoreCardPolicy {
    override fun isMoreCard(name: PlayerName): Boolean {
        return false
    }
}
