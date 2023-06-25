package blackjack

import blackjack.domain.CardType
import blackjack.domain.CardValue
import blackjack.domain.PlayingCard
import blackjack.domain.PlayingCardPackFactory
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.collections.shouldNotContainInOrder

class PlayingCardPackTest : FreeSpec({

    "새 카드 팩은 서로 다른 카드 52장으로 이루어진다." {
        val pack = PlayingCardPackFactory.createPack()
        val playingCards = CardType.values().flatMap { type -> CardValue.values().map { value -> PlayingCard(type, value) } }

        pack shouldHaveSize 52
        pack shouldContainAll playingCards
    }

    "카드 팩은 섞을 수 있다." {
        val pack = PlayingCardPackFactory.createPack()
        val originOrder = pack.toList()
        pack.shuffle()
        val shuffledOrder = pack.toList()

        shuffledOrder shouldNotContainInOrder originOrder
    }
})
