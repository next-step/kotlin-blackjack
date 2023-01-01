package com.nextstep.blackjack.domain

import com.nextstep.blackjack.domain.card.Card
import com.nextstep.blackjack.domain.card.CardNumber.ACE
import com.nextstep.blackjack.domain.card.CardNumber.FOUR
import com.nextstep.blackjack.domain.card.CardNumber.KING
import com.nextstep.blackjack.domain.card.CardNumber.TEN
import com.nextstep.blackjack.domain.card.CardNumber.TWO
import com.nextstep.blackjack.domain.card.CardPattern.SPADE
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import io.kotest.matchers.throwable.shouldHaveMessage

class PlayerTest : BehaviorSpec({
    Given("빈값의 이름이 주어지고") {
        When("Player를 생성하면") {
            Then("예외가 발생한다") {
                shouldThrow<IllegalArgumentException> { Player(" ") } shouldHaveMessage "이름은 빈 값일 수 없습니다."
            }
        }
    }

    Given("Player.calculateScore") {
        When("ACE 카드가 없으면") {
            Then("카드의 score 합친 점수가 나온다") {
                forAll(
                    row(listOf(TWO, TEN), 12),
                    row(listOf(KING, TEN), 20),
                    row(listOf(TWO, FOUR), 6),
                ) {
                    cardNumbers, score ->
                    val cards = cardNumbers.map { Card(it, SPADE) }
                    val player = Player("플레이어", cards)

                    player.calculateScore() shouldBe score
                }
            }
        }

        When("ACE 카드가 있으면") {
            Then("ACE 카드는 11로 계산될 수 있다") {
                forAll(
                    row(listOf(ACE, TEN), 21),
                    row(listOf(ACE, TWO), 13),
                    row(listOf(ACE, ACE, TWO), 14),
                ) {
                        cardNumbers, score ->
                    val cards = cardNumbers.map { Card(it, SPADE) }
                    val player = Player("플레이어", cards)

                    player.calculateScore() shouldBe score
                }
            }
        }
    }
})
