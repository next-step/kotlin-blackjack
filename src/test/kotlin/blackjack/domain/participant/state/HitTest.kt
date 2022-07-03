package blackjack.domain.participant.state

import blackjack.domain.deck.Card
import blackjack.domain.deck.CardNumber
import blackjack.domain.deck.CardPattern
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf

internal class HitTest : FreeSpec({

    fun getHitScoreCards(): MutableList<Card> {
        return mutableListOf(
            Card(pattern = CardPattern.CLOVER, number = CardNumber.TEN),
            Card(pattern = CardPattern.HEART, number = CardNumber.NINE)
        ).toMutableList()
    }

    "힛에서" - {
        "카드 받기를 요청했을 때" - {
            "받은 카드의 합이 21을 넘으면 버스트가 된다." {
                val hit = Hit(cards = Cards(values = getHitScoreCards()))
                val card = Card(pattern = CardPattern.SPADE, number = CardNumber.TEN)
                println(hit.cards().map { it.number.score })
                hit.receiveCard(card = card).shouldBeInstanceOf<Bust>()
            }

            "받은 카드의 합이 21 이하일 경우 힛을 유지한다." {
                val hit = Hit(cards = Cards(values = getHitScoreCards()))
                val card = Card(pattern = CardPattern.SPADE, number = CardNumber.TWO)
                hit.receiveCard(card = card).shouldBeInstanceOf<Hit>()
            }
        }

        "스테이로 상태변화를 요청하면 스테이로 변경된다." {
            val hit = Hit(cards = Cards(values = getHitScoreCards()))
            hit.stay().shouldBeInstanceOf<Stay>()
        }

        "종료되었는지 물으면 False를 반환한다." {
            val hit = Hit(cards = Cards(values = getHitScoreCards()))
            hit.isFinished() shouldBe false
        }

        "카드 목록을 반환할 수 있다." {
            val hit = Hit(cards = Cards(values = getHitScoreCards()))
            hit.cards().shouldContainExactly(getHitScoreCards())
        }

        "점수를 반환할 수 있다." {
            val hit = Hit(cards = Cards(values = getHitScoreCards()))
            hit.score().value shouldBe 19
        }

        "다른 점수와 승패를 비교하려하면 예외가 발생한다." {
            val hit = Hit(cards = Cards(values = getHitScoreCards()))
            shouldThrowExactly<IllegalStateException> { hit.judgementGameResult(otherScore = Score(10)) }
        }
    }
})
