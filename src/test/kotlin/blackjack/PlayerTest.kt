package blackjack

import io.kotest.core.spec.style.FreeSpec
import io.kotest.datatest.withData
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe

class PlayerTest : FreeSpec({
    "플레이어는 이름을 갖는다." - {
        withData(
            "제이든",
            "김진억"
        ) { name ->
            val player = Player(name)

            player.name shouldBe name
        }
    }

    "플레이어는 카드를 받는다." {
        val player = Player("제이든")
        val cards = listOf(
            PlayingCard(CardType.CLUB, CardValue.ACE),
            PlayingCard(CardType.DIAMOND, CardValue.ACE),
            PlayingCard(CardType.SPADE, CardValue.TEN),
            PlayingCard(CardType.HEART, CardValue.KING)
        )

        cards.forEach { player.receive(it) }

        player.cards shouldContainExactly cards
    }
})
