package blackjack.domain.card

import blackjack.domain.deck.Denomination
import blackjack.domain.deck.Suit
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe
import io.kotest.matchers.throwable.shouldHaveMessage

class PlayingCardsTest : BehaviorSpec({

    Given(name = "게임에서 사용할 카드가 주어진 경우에") {
        val expect = mutableSetOf(
            Card(denomination = Denomination.ACE, suit = Suit.CLUBS),
            Card(denomination = Denomination.QUEEN, suit = Suit.DIAMONDS),
        )

        When(name = "플레잉 카드 리스트에 등록하면") {
            val playingCards = PlayingCards(cards = expect)

            Then(name = "해당 카드 리스트가 들어가있다.") {
                playingCards shouldBe expect
            }
        }

        val addExpect = Card(denomination = Denomination.FIVE, suit = Suit.CLUBS)
        val playingCards = PlayingCards(cards = expect)

        When(name = "새로운 카드를 추가하면") {
            playingCards.add(card = addExpect)

            Then(name = "추가한 카드가 들어가있다.") {
                playingCards shouldContain addExpect
            }
        }

        When(name = "이미 추가된 카드를 추가하면") {
            val exception = shouldThrow<IllegalStateException> {
                playingCards.add(card = addExpect)
            }

            Then(name = "중복된 카드를 등록할 수 없다는 에러가 발생한다.") {
                exception shouldHaveMessage "중복된 카드를 추가할 수 없습니다. 카드 : $addExpect"
            }
        }

        When(name = "정해진 버스트 값보다 작으면") {
            val actual = playingCards.isBust()

            Then(name = "거짓을 반환한다.") {
                actual shouldBe false
            }
        }

        playingCards.add(card = Card(denomination = Denomination.JACK, suit = Suit.DIAMONDS))

        When(name = "정해진 버스트 값을 넘으면") {
            val actual = playingCards.isBust()

            Then(name = "참을 반환한다.") {
                actual shouldBe true
            }
        }
    }
})
