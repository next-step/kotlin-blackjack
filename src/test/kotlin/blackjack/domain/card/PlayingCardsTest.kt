package blackjack.domain.card

import blackjack.domain.deck.Denomination
import blackjack.domain.deck.Suit
import blackjack.domain.model.BlackJackErrorCode
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe
import io.kotest.matchers.throwable.shouldHaveMessage

class PlayingCardsTest : BehaviorSpec({

    Given(name = "게임에서 사용할 카드를 생성할 수 있다.") {
        val expect = mutableSetOf(
            Card(denomination = Denomination.ACE, suit = Suit.CLUBS),
            Card(denomination = Denomination.QUEEN, suit = Suit.DIAMONDS),
        )

        When(name = "주어진 카드 리스트를 등록하게 되면") {
            val playingCards = MockPlayingCards(cards = expect)

            Then(name = "해당 카드 리스트가 들어가있다.") {
                playingCards shouldBe expect
            }
        }

        val addExpect = Card(denomination = Denomination.FIVE, suit = Suit.CLUBS)
        val playingCards = MockPlayingCards(cards = expect)

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
                exception shouldHaveMessage BlackJackErrorCode.CAN_NOT_ADD_DUPLICATE_CARD.message(arrayOf(addExpect))
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

    Given(name = "플레이 카드 리스트가 주어지면") {
        val cards = mutableSetOf(
            Card(denomination = Denomination.ACE, suit = Suit.CLUBS),
            Card(denomination = Denomination.QUEEN, suit = Suit.DIAMONDS),
        )

        When(name = "최적의 점수를 계산할 수 있다.") {
            val playingCards = MockPlayingCards(cards = cards)

            Then(name = "ACE가 11로 판단되어도 21 미만이면 11로 판단한다.") {
                playingCards.calculateOptimalScore() shouldBe 11 + 10
            }

            playingCards.add(card = Card(denomination = Denomination.QUEEN, suit = Suit.CLUBS))

            Then(name = "ACE가 11로 판단되어 21 이상이면 1로 판단한다.") {
                playingCards.calculateOptimalScore() shouldBe 1 + 10 + 10
            }
        }
    }
})
