package blackjack

import blackjack.state.Blackjack
import blackjack.state.Bust
import blackjack.state.Hit
import blackjack.state.PlayerState
import blackjack.state.Start

class Player(
    private val name: String,
    private val hand: Cards = Cards(),
    private val state: PlayerState = Start,
) {
    fun addCardToHand(card: Card): Player {
        return Player(name = this.name, hand = this.hand.add(card))
    }

    fun handSize(): Int {
        return this.hand.size()
    }

    fun totalScoreInHand(): Score {
        return this.hand.totalScore()
    }

    fun isStart(): Boolean = this.state.isStart()
    fun isHit(): Boolean = this.state.isHit()
    fun isStay(): Boolean = this.state.isStay()
    fun isBust(): Boolean = this.state.isBust()
    fun isBlackjack(): Boolean = this.state.isBlackjack()

    fun hit(): Player {
        if (hand.totalScore().isBlackjack() || hand.totalScore().isBust()) {
            throw IllegalStateException("손패의 총 합이 21이상이면 hit할 수 없습니다.")
        }
        return Player(name = this.name, hand = this.hand, state = Hit)
    }

    fun stay(): Player {
        if (hand.totalScore().isLessThan(Score(20))) {
            return Player(name = this.name, hand = this.hand, state = this.state.stay())
        }
        throw IllegalStateException("손패 총합이 21을 이상이면 stay 상태가 될 수 없습니다.")
    }

    fun bust(): Player {
        if (hand.totalScore().isBust()) {
            return Player(name = this.name, hand = this.hand, state = Bust)
        }
        throw IllegalStateException("손패 총합이 22 이상이 아니므로 Bust가 아닙니다.")
    }

    fun blackjack(): Player {
        if (hand.totalScore().isBlackjack()) {
            return Player(name = this.name, hand = this.hand, state = Blackjack)
        }
        throw IllegalStateException("손패 총합이 21이 아니므로 Blackjack이 아닙니다.")
    }
}
