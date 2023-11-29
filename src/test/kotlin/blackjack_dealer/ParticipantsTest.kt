package blackjack_dealer

import blackjack_dealer.domain.Dealer
import blackjack_dealer.entity.AllParticipantWithBetAmount
import blackjack_dealer.entity.CardDeque
import blackjack_dealer.entity.Participants
import blackjack_dealer.entity.card.Card
import blackjack_dealer.entity.card.CardNumber
import blackjack_dealer.entity.card.CardShape
import blackjack_dealer.entity.toGamerCards
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class ParticipantsTest : StringSpec({
    "전달한 이름의 수만큼 참가자를 생성했다" {
        val input = "pita,haero,sery"
        val expected = 3
        CardDeque().create()
        val allParticipantWithBetAmount = AllParticipantWithBetAmount.newInstance(
            names = input,
            betAmounts = listOf(1, 2, 3)
        )
        Participants.newInstance(
            allParticipantWithBetAmount,
            cardDeque = { listOf(Card(CardNumber.TWO, CardShape.CLOVER)).toGamerCards() },
        ).size shouldBe expected
    }

    "참가자가 승,승 인경우 딜러의 손실은 두 참가자의 금액을 합한 값의 음수 이다" {
        val input = "pita,haero"
        val expected = -3000
        var firstInput = true

        val allParticipantWithBetAmount = AllParticipantWithBetAmount.newInstance(
            names = input,
            betAmounts = listOf(1000, 2000)
        )
        val participants = Participants.newInstance(
            allParticipantWithBetAmount,
            cardDeque = {
                if (firstInput) {
                    firstInput = false
                    listOf(
                        Card(CardNumber.Q, CardShape.CLOVER),
                        Card(CardNumber.J, CardShape.CLOVER),
                    ).toGamerCards()
                } else {
                    listOf(
                        Card(CardNumber.Q, CardShape.HEART),
                        Card(CardNumber.THREE, CardShape.HEART),
                    ).toGamerCards()
                }
            }
        )
        val dealer = Dealer.newInstance(
            listOf(
                Card(CardNumber.K, CardShape.CLOVER),
                Card(CardNumber.TWO, CardShape.CLOVER),
            ).toGamerCards()
        )
        participants.first().getCurrentCards().getCurrentScore() shouldBe 20
        participants.last().getCurrentCards().getCurrentScore() shouldBe 13
        participants.getParticipantsLoss(dealer) shouldBe expected
    }

    "참가자가 승,패 인경우 딜러의 손실은 두 참가자의 금액을 합한 값의 음수 이다" {
        val input = "pita,haero"
        val expected = 1000
        var firstInput = true

        val allParticipantWithBetAmount = AllParticipantWithBetAmount.newInstance(
            names = input,
            betAmounts = listOf(1000, 2000)
        )
        val participants = Participants.newInstance(
            allParticipantWithBetAmount,
            cardDeque = {
                if (firstInput) {
                    firstInput = false
                    listOf(
                        Card(CardNumber.Q, CardShape.CLOVER),
                        Card(CardNumber.J, CardShape.CLOVER),
                    ).toGamerCards()
                } else {
                    listOf(
                        Card(CardNumber.Q, CardShape.HEART),
                        Card(CardNumber.THREE, CardShape.HEART),
                    ).toGamerCards()
                }
            }
        )
        val dealer = Dealer.newInstance(
            listOf(
                Card(CardNumber.K, CardShape.CLOVER),
                Card(CardNumber.FIVE, CardShape.CLOVER),
            ).toGamerCards()
        )
        participants.first().getCurrentCards().getCurrentScore() shouldBe 20
        participants.last().getCurrentCards().getCurrentScore() shouldBe 13
        participants.getParticipantsLoss(dealer) shouldBe expected
    }

    "참가자가 패,패 인경우 딜러의 손실은 두 참가자의 금액을 합한 값의 음수 이다" {
        val input = "pita,haero"
        val expected = 3000
        var firstInput = true

        val allParticipantWithBetAmount = AllParticipantWithBetAmount.newInstance(
            names = input,
            betAmounts = listOf(1000, 2000)
        )
        val participants = Participants.newInstance(
            allParticipantWithBetAmount,
            cardDeque = {
                if (firstInput) {
                    firstInput = false
                    listOf(
                        Card(CardNumber.Q, CardShape.CLOVER),
                        Card(CardNumber.FOUR, CardShape.CLOVER),
                    ).toGamerCards()
                } else {
                    listOf(
                        Card(CardNumber.Q, CardShape.HEART),
                        Card(CardNumber.THREE, CardShape.HEART),
                    ).toGamerCards()
                }
            }
        )
        val dealer = Dealer.newInstance(
            listOf(
                Card(CardNumber.K, CardShape.CLOVER),
                Card(CardNumber.FIVE, CardShape.CLOVER),
            ).toGamerCards()
        )
        participants.first().getCurrentCards().getCurrentScore() shouldBe 14
        participants.last().getCurrentCards().getCurrentScore() shouldBe 13
        participants.getParticipantsLoss(dealer) shouldBe expected
    }

    "참가자가 블랙잭 인경우 1.5배를 잃는다" {
        val input = "pita"
        val expected = -1500

        val allParticipantWithBetAmount = AllParticipantWithBetAmount.newInstance(
            names = input,
            betAmounts = listOf(1000)
        )
        val participants = Participants.newInstance(
            allParticipantWithBetAmount,
            cardDeque = {
                listOf(
                    Card(CardNumber.Q, CardShape.CLOVER),
                    Card(CardNumber.A, CardShape.CLOVER),
                ).toGamerCards()
            }
        )
        val dealer = Dealer.newInstance(
            listOf(
                Card(CardNumber.K, CardShape.CLOVER),
                Card(CardNumber.FIVE, CardShape.CLOVER),
            ).toGamerCards()
        )
        participants.first().getCurrentCards().getCurrentScore() shouldBe 21
        participants.getParticipantsLoss(dealer) shouldBe expected
    }

    "참가자와 딜러가 블랙잭 인경우 둘다 0원이다" {
        val input = "pita"
        val expected = 0

        val allParticipantWithBetAmount = AllParticipantWithBetAmount.newInstance(
            names = input,
            betAmounts = listOf(1000)
        )
        val participants = Participants.newInstance(
            allParticipantWithBetAmount,
            cardDeque = {
                listOf(
                    Card(CardNumber.Q, CardShape.CLOVER),
                    Card(CardNumber.A, CardShape.CLOVER),
                ).toGamerCards()
            }
        )
        val dealer = Dealer.newInstance(
            listOf(
                Card(CardNumber.K, CardShape.CLOVER),
                Card(CardNumber.A, CardShape.HEART),
            ).toGamerCards()
        )
        participants.first().getCurrentCards().getCurrentScore() shouldBe 21
        participants.getParticipantsLoss(dealer) shouldBe expected
    }

    "참가자가 HIT, BUST 일 때, 딜러가 BUST인 경우 HIT만 돈을 지급한다" {
        val input = "pita,haero"
        val expected = 1000
        var firstInput = true

        val allParticipantWithBetAmount = AllParticipantWithBetAmount.newInstance(
            names = input,
            betAmounts = listOf(1000, 2000)
        )
        val participants = Participants.newInstance(
            allParticipantWithBetAmount,
            cardDeque = {
                if (firstInput) {
                    firstInput = false
                    listOf(
                        Card(CardNumber.Q, CardShape.CLOVER),
                        Card(CardNumber.SIX, CardShape.CLOVER),
                    ).toGamerCards()
                } else {
                    listOf(
                        Card(CardNumber.Q, CardShape.HEART),
                        Card(CardNumber.J, CardShape.HEART),
                        Card(CardNumber.THREE, CardShape.HEART),
                    ).toGamerCards()
                }
            }
        )
        val dealer = Dealer.newInstance(
            listOf(
                Card(CardNumber.K, CardShape.CLOVER),
                Card(CardNumber.FIVE, CardShape.CLOVER),
                Card(CardNumber.TEN, CardShape.CLOVER),
            ).toGamerCards()
        )
        participants.first().getCurrentCards().getCurrentScore() shouldBe 16
        participants.last().getCurrentCards().getCurrentScore() shouldBe 23
        participants.getParticipantsLoss(dealer) shouldBe expected
    }
})
