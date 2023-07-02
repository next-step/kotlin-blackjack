package blackjack.domain

import blackjack.fixture.ACE_SPADE
import blackjack.fixture.KING_DIAMOND
import blackjack.fixture.KING_HEART
import blackjack.fixture.SEVEN_HEART
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class HandTest : BehaviorSpec({
    given("BURST 상태의 Hand에 대해") {
        val openCards = OpenCards(KING_HEART, KING_DIAMOND)
        val hand = Hand.create(openCards)
        hand.add(SEVEN_HEART)
        `when`("카드를 추가 하려고 하면") {
            then("오류가 발생 한다.") {
                shouldThrow<RuntimeException> { hand.add(ACE_SPADE) }
            }
        }
        `when`("버스트 상태를 조회 하면") {
            then("true를 반환 한다.") {
                hand.bust() shouldBe true
            }
        }
    }
    given("BURST가 아니고 ACE를 가진 Hand가 있을 때") {
        val openCards = OpenCards(KING_HEART, ACE_SPADE)
        val hand = Hand.create(openCards)
        `when`("핸드의 합을 요청 하면") {
            then("소프트 점수가 계산 되어 반환 된다.") {
                hand.total() shouldBe 21
            }
        }
        `when`("버스트 상태를 조회 하면") {
            then("false를 반환 한다.") {
                hand.bust() shouldBe false
            }
        }
    }
    given("BURST가 아니고 ACE가 없는 Hand가 있을 때") {
        val openCards = OpenCards(KING_HEART, SEVEN_HEART)
        val hand = Hand.create(openCards)
        `when`("핸드의 합을 요청 하면") {
            then("하드 점수가 계산 되어 반환 된다.") {
                hand.total() shouldBe 17
            }
        }
        `when`("버스트 상태를 조회 하면") {
            then("false를 반환 한다.") {
                hand.bust() shouldBe false
            }
        }
    }
})
