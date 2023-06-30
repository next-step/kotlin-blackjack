package blackjack

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

class GameCardStorageTest : FreeSpec({
    "게임에서 사용될 카드들은 조커를 제외한 52장이다. - 모든 종류, 숫자별 1장씩" {
        val allPossibleUniqueCards = CardNumber
            .values()
            .flatMap { number ->
                Suit.values().map { suit ->
                    Card(suit, number)
                }
            }
            .shuffled()
            .toSet()

        val sut = GameCardStorage(allPossibleUniqueCards)

        sut.currentCards shouldContainExactly allPossibleUniqueCards
        sut.currentCards shouldHaveSize 52
    }

    "게임에서 사용될 카드가 충분하지 못할 경우 예외를 던진다" {
        val insufficientCards = setOf(Card(Suit.SPADE, CardNumber.ACE), Card(Suit.CLUB, CardNumber.ACE))

        val exception = shouldThrow<IllegalArgumentException> {
            GameCardStorage(insufficientCards)
        }

        exception.message shouldBe "게임에서 사용될 카드들은 조커를 제외한 모든 트럼프 카드 52장이어야 합니다."
    }

    "게임에서 사용할 카드는 1장씩 입력한 순서대로 뽑을 수 있다." {
        val allPossibleUniqueCards = CardNumber
            .values()
            .flatMap { number ->
                Suit.values().map { suit ->
                    Card(suit, number)
                }
            }
            .shuffled()
            .toSet()

        val sut = GameCardStorage(allPossibleUniqueCards)

        allPossibleUniqueCards.forEach {
            sut.drawCard() shouldBe it
        }
    }
})
