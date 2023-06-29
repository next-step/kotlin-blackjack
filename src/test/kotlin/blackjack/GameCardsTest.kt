package blackjack

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder

class GameCardsTest : FreeSpec({
    "게임에서 사용될 카드는 조커를 제외한 52장이다. - 모든 종류, 숫자별 1장씩" {
        val allPossibleUniqueCards = CardNumber.values().flatMap { number ->
            Suit.values().map { suit ->
                Card(suit, number)
            }
        }

        val sut = GameCards()

        sut.cards shouldContainExactlyInAnyOrder allPossibleUniqueCards
    }
}
)
