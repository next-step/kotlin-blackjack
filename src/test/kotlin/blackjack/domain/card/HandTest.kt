package blackjack.domain.card

import blackjack.domain.player.OpenCards
import blackjack.fixture.DIAMOND_KING
import blackjack.fixture.HEART_KING
import blackjack.fixture.HEART_SEVEN
import blackjack.fixture.SPADE_ACE
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class HandTest : BehaviorSpec({
    given("버스트 상태의 Hand에 대해") {
        val openCards = OpenCards(HEART_KING, DIAMOND_KING)
        val hand = Hand.create(openCards)
        hand.add(HEART_SEVEN)
        `when`("카드를 추가 하려고 하면") {
            then("오류가 발생 한다.") {
                shouldThrow<RuntimeException> { hand.add(SPADE_ACE) }
            }
        }
        `when`("버스트 상태를 조회 하면") {
            then("true를 반환 한다.") {
                hand.bust() shouldBe true
            }
        }
    }
    given("버스트가 아니고 ACE를 가진 Hand가 있을 때") {
        val openCards = OpenCards(HEART_KING, SPADE_ACE)
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
    given("버스트가 아니고 ACE가 없는 Hand가 있을 때") {
        val openCards = OpenCards(HEART_KING, HEART_SEVEN)
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
