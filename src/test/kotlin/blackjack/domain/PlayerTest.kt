package blackjack.domain

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import java.lang.IllegalArgumentException

class PlayerTest : FunSpec({
    context("객체 생성") {
        test("이름, 카드를 입력받아 플레이어 객체를 생성한다.") {
            shouldNotThrowAny {
                val cards = linkedSetOf(Card(CardPattern.DIAMOND, CardValue.JACK), Card(CardPattern.DIAMOND, CardValue.ACE))
                Player("name", Cards(cards))
            }
        }
        test("이름이 빈 문자열일 경우 예외가 발생한다.") {
            val cards = linkedSetOf(Card(CardPattern.DIAMOND, CardValue.JACK), Card(CardPattern.DIAMOND, CardValue.ACE))
            shouldThrow<IllegalArgumentException> {
                Player("", Cards(cards))
            }
        }
        test("카드가 2장이 아닐 경우 예외가 발생한다.") {
            val oneCard = linkedSetOf(Card(CardPattern.DIAMOND, CardValue.JACK))
            val threeCards = linkedSetOf(Card(CardPattern.CLOVER, CardValue.JACK), Card(CardPattern.CLOVER, CardValue.ACE), Card(CardPattern.CLOVER, CardValue.THREE))
            shouldThrow<IllegalArgumentException> {
                Player("name", Cards(oneCard))
                Player("name", Cards(threeCards))
            }
        }
    }
})
