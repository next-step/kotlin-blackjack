package blackjack.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.collections.shouldNotEndWith
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.equals.shouldNotBeEqual
import io.kotest.matchers.nulls.shouldNotBeNull
import java.util.*

object TestShufflingStrategy : ShufflingStrategy {
    override fun shuffle(cards: Collection<Card>): Queue<Card> {
        return LinkedList(cards.shuffled(Random(1)))
    }
}

class CardsTest : FreeSpec({
    "카드 목록은 스페이드/하트/클로버/다이아 각각 13장씩 총 52장이 들어있다" {
        val cards = Cards.create()

        cards shouldHaveSize 52

        cards.groupBy { card -> card.suit }
            .values
            .forEach { cardsOfSuit ->
                cardsOfSuit shouldHaveSize 13
            }
    }

    "전략에 따라 카드를 Shuffling 할 수 있다" {
        val cards = Cards.create()

        val shuffledCards = Cards.create(TestShufflingStrategy)

        cards shouldNotBeEqual shuffledCards
    }

    "draw() 테스트" - {
        "카드를 하나 뽑을 수 있다" {
            val cards = Cards.create()

            val card: Card = cards.draw()

            card.shouldNotBeNull()
            cards shouldHaveSize 51
        }

        "더이상 뽑을 수 있는 카드가 없을 경우 예외를 발생시킨다" {
            val cards = Cards.create()

            repeat(52) {
                cards.draw()
            }

            shouldThrow<CannotDrawCardsException> { cards.draw() }
        }
    }
})