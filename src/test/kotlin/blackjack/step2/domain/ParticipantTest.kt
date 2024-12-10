package blackjack.step2.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class ParticipantTest : FunSpec({
    context("isBust() 테스트") {
        test("21을 초과하면 true를 반환한다.") {
            // given
            val participant = Player("dongyeon") as Participant
            val card1 = Card(CardNumber.TEN, CardType.SPADE)
            val card2 = Card(CardNumber.KING, CardType.HEART)
            val card3 = Card(CardNumber.TWO, CardType.DIAMOND)
            val card4 = Card(CardNumber.ACE, CardType.CLOVER)

            // when
            val pickedParticipant =
                participant.pickCard(card1)
                    .pickCard(card2)
                    .pickCard(card3)
                    .pickCard(card4)

            // then
            pickedParticipant.isBust() shouldBe true
        }
        test("21을 초과하지 않으면 false를 반환한다") {
            // given
            val participant = Player("dongyeon") as Participant
            val card1 = Card(CardNumber.TEN, CardType.SPADE)

            // when
            val pickedParticipant = participant.pickCard(card1)

            // then
            pickedParticipant.isBust() shouldBe false
        }
    }
    context("score() 테스트") {
        test("플레이어가 갖고 있는 카드의 합을 반환한다.") {
            // given
            val participant = Player("dongyeon") as Participant
            val card1 = Card(CardNumber.TWO, CardType.SPADE)
            val card2 = Card(CardNumber.THREE, CardType.HEART)
            val card3 = Card(CardNumber.FOUR, CardType.DIAMOND)
            val card4 = Card(CardNumber.FIVE, CardType.CLOVER)

            // when
            val pickedParticipant =
                participant.pickCard(card1)
                    .pickCard(card2)
                    .pickCard(card3)
                    .pickCard(card4)

            // then
            pickedParticipant.score() shouldBe 14
        }
    }
})
