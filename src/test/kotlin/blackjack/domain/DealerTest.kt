package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

interface Player2 {
    val name: String
    val cards: Cards

    fun initialCard(cards: Cards): Player2 = copy(cards = this.cards.plus(cards))

    fun hit(card: Card): Player2 {
        check(!isBurst()) { "카드를 받을 수 없습니다." }
        return copy(cards = this.cards.plus(card))
    }

    fun countingCard(): Int = cards.countingCard()

    fun isBurst(): Boolean = countingCard() > BLACKJACK_SCORE

    fun copy(name: String = this.name, cards: Cards): Player2
}

class Dealer(override val cards: Cards = Cards()) : Player2 {
    override val name: String = NAME
    override fun copy(name: String, cards: Cards): Player2 {
        return Dealer(cards = cards)
    }
    
    companion object {
        const val NAME: String = "딜러"
        const val DEALER_HIT_RULE_SCORE = 17
    }
}

class DealerTest {

    @Test
    fun `딜러라는 이름을 가진다`() {
        Dealer().name shouldBe "딜러"
    }
}
