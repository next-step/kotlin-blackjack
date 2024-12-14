package blackjack.policy

import blackjack.participant.PlayerName

interface MoreCardPolicy {
    fun isMoreCard(name: PlayerName): Boolean
}
