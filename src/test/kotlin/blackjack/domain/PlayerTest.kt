package blackjack.domain

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.data.forAll
import io.kotest.data.headers
import io.kotest.data.row
import io.kotest.data.table
import io.kotest.matchers.shouldBe

class PlayerTest : FunSpec({
    context("객체 생성") {
        test("이름, 카드를 입력받아 플레이어 객체를 생성한다.") {
            shouldNotThrowAny {
                val cards = linkedSetOf(Card(CardPattern.DIAMOND, CardValue.NINE), Card(CardPattern.DIAMOND, CardValue.ACE))
                Player("name", Cards(cards))
            }
        }
        test("초기 카드 2장의 합이 21일 경우, 블랙잭 상태로 변경된다.") {
            table(
                headers("player", "expectedResult"),
                row(Player("name1", Cards(linkedSetOf(Card(CardPattern.DIAMOND, CardValue.JACK), Card(CardPattern.DIAMOND, CardValue.ACE)))), PlayerState.Done.BlackJack),
                row(Player("name2", Cards(linkedSetOf(Card(CardPattern.DIAMOND, CardValue.JACK), Card(CardPattern.DIAMOND, CardValue.KING)))), PlayerState.Play.Idle)
            ).forAll { player, expectedResult ->
                player.state shouldBe expectedResult
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
    context("hit()") {
        test("입력받은 카드를 추가하고 그에 따른 상태를 변경한다.") {
            val cards = linkedSetOf(Card(CardPattern.DIAMOND, CardValue.EIGHT), Card(CardPattern.DIAMOND, CardValue.TEN))

            table(
                headers("player", "card", "expectedResult"),
                row(Player("name", Cards(cards)), Card(CardPattern.DIAMOND, CardValue.FOUR), PlayerState.Done.Burst),
                row(Player("name", Cards(cards)), Card(CardPattern.CLOVER, CardValue.THREE), PlayerState.Done.BlackJack),
                row(Player("name", Cards(cards)), Card(CardPattern.CLOVER, CardValue.ACE), PlayerState.Play.Hit),
            ).forAll { player, card, expectedResult ->
                player.hit(card)
                player.state shouldBe expectedResult
            }
        }
    }
    context("stay()") {
        test("stay 상태로 변경한다.") {
            val cards = linkedSetOf(Card(CardPattern.DIAMOND, CardValue.EIGHT), Card(CardPattern.DIAMOND, CardValue.TEN))
            val player = Player("name", Cards(cards))

            player.stay()

            player.state shouldBe PlayerState.Done.Stay
        }
    }
})
