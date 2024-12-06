package blackjack.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class PlayerTest : StringSpec({
    "플레이어의 이름을 반환할 수 있다." {
        val player = Player(PlayerName("dino"), Hand())
        player.getName() shouldBe "dino"
    }

    "카드를 추가로 뽑을 수 있는 상황인지 확인할 수 있다." {
        forAll(
            row(listOf(Card(Rank.ACE, Suit.HEARTS), Card(Rank.TWO, Suit.DIAMONDS)), true),
            row(listOf(Card(Rank.ACE, Suit.SPADES), Card(Rank.TEN, Suit.DIAMONDS)), false),
            row(listOf(Card(Rank.JACK, Suit.HEARTS), Card(Rank.KING, Suit.HEARTS)), true),
            row(
                listOf(Card(Rank.EIGHT, Suit.HEARTS), Card(Rank.EIGHT, Suit.DIAMONDS), Card(Rank.SIX, Suit.DIAMONDS)),
                false,
            ),
            row(
                listOf(Card(Rank.EIGHT, Suit.HEARTS), Card(Rank.EIGHT, Suit.DIAMONDS), Card(Rank.FIVE, Suit.DIAMONDS)),
                true,
            ),
        ) { cards, expected ->
            val player = Player.createNew(PlayerName("dino"), cards)
            player.isDrawable() shouldBe expected
        }
    }

    "플레이어의 카드의 합을 반환할 수 있다." {
        forAll(
            row(listOf(Card(Rank.ACE, Suit.HEARTS), Card(Rank.TWO, Suit.DIAMONDS)), 13),
            row(listOf(Card(Rank.TWO, Suit.HEARTS), Card(Rank.KING, Suit.SPADES)), 12),
            row(listOf(Card(Rank.KING, Suit.HEARTS), Card(Rank.NINE, Suit.CLOVERS)), 19),
            row(listOf(Card(Rank.JACK, Suit.HEARTS), Card(Rank.KING, Suit.HEARTS)), 20),
            row(listOf(Card(Rank.QUEEN, Suit.HEARTS), Card(Rank.FIVE, Suit.HEARTS)), 15),
            row(listOf(Card(Rank.ACE, Suit.DIAMONDS), Card(Rank.TEN, Suit.HEARTS)), 21),
            row(listOf(Card(Rank.ACE, Suit.DIAMONDS), Card(Rank.ACE, Suit.CLOVERS), Card(Rank.ACE, Suit.HEARTS)), 13),
        ) { cards, expected ->
            val player = Player.createNew(PlayerName("dino"), cards)
            player.calculateTotal() shouldBe expected
        }
    }
})
