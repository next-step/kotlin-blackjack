package blackjack.domain

import io.kotest.assertions.throwables.shouldNotThrow
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

class CardTest {

    @ParameterizedTest
    @EnumSource(CardNumber::class)
    fun `카드의 숫자는 2~9, 또는 K,Q,J(10) A가 올 수 있음`(cardNumber: CardNumber) {
        shouldNotThrow<IllegalStateException> {
            Card(cardNumber, CardType.DIAMOND)
        }
    }

    @ParameterizedTest
    @EnumSource(CardType::class)
    fun `카드의 종류는 스페이드, 클로버, 하트, 다이아몬드가 올 수 있음`(cardType: CardType) {
        shouldNotThrow<IllegalStateException> {
            Card(CardNumber.CARD_TWO, cardType)
        }
    }
}
