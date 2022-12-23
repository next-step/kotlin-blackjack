package blackjack.domain

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class PlayerTest : StringSpec({
    "이름이 빈 문자열이면 에러 발생" {
        val exception = shouldThrowExactly<IllegalArgumentException> {
            Player("")
        }
        exception.message shouldBe "플레이어 이름은 한글자 이상이여야 합니다."
    }

    "이름이 공백이면 에러 발생" {
        val exception = shouldThrowExactly<IllegalArgumentException> {
            Player(" ")
        }
        exception.message shouldBe "플레이어 이름은 한글자 이상이여야 합니다."
    }

    "중복되는 카드이면 에러 발생" {
        // given
        val player = Player("Kim")
        val exception = shouldThrowExactly<IllegalArgumentException> {
            player.give(Card(CardSuit.CLUB, CardNumber.THREE))
            player.give(Card(CardSuit.CLUB, CardNumber.THREE))
        }
        exception.message shouldBe "중복되는 카드가 있습니다."
    }

    "플레이어에 카드 추가 테스트" {
        // given
        val player = Player("kim")
        // when
        player.give(Card(CardSuit.CLUB, CardNumber.THREE))
        player.give(Card(CardSuit.SPADE, CardNumber.TEN))
        // then
        player.cards shouldBe listOf(Card(CardSuit.CLUB, CardNumber.THREE), Card(CardSuit.SPADE, CardNumber.TEN))
    }

    "플레이어에 점수 계산 테스트" {
        forAll(
            row(listOf(Card(CardSuit.CLUB, CardNumber.FIVE), Card(CardSuit.CLUB, CardNumber.EIGHT)), 13.toScore()),
            row(listOf(Card(CardSuit.CLUB, CardNumber.ACE), Card(CardSuit.CLUB, CardNumber.QUEEN)), 21.toScore()),
            row(listOf(Card(CardSuit.CLUB, CardNumber.ACE), Card(CardSuit.DIAMOND, CardNumber.QUEEN), Card(CardSuit.SPADE, CardNumber.JACK)), 21.toScore()),
        ) { cards, expectedScore ->
            // given
            val player = Player("kim")
            // when
            cards.forEach {
                player.give(it)
            }
            // then
            player.totalScore() shouldBe expectedScore
        }
    }
})
