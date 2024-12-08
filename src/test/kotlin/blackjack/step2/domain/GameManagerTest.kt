package blackjack.step2.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.ints.shouldBeGreaterThanOrEqual
import io.kotest.matchers.shouldBe

class GameManagerTest : FunSpec({
    val initialCards =
        listOf(
            Card(CardNumber.ACE, CardType.SPADE),
            Card(CardNumber.TWO, CardType.SPADE),
            Card(CardNumber.THREE, CardType.SPADE),
            Card(CardNumber.FOUR, CardType.SPADE),
            Card(CardNumber.FIVE, CardType.SPADE),
            Card(CardNumber.SIX, CardType.SPADE),
            Card(CardNumber.SEVEN, CardType.SPADE),
            Card(CardNumber.EIGHT, CardType.SPADE),
            Card(CardNumber.NINE, CardType.SPADE),
            Card(CardNumber.TEN, CardType.SPADE),
            Card(CardNumber.KING, CardType.SPADE),
            Card(CardNumber.QUEEN, CardType.SPADE),
            Card(CardNumber.JACK, CardType.SPADE),
        )
    val mockCardPicker =
        object : CardPicker {
            private val availableCards = initialCards.toMutableList()

            override fun pick(): Card {
                if (availableCards.isEmpty()) throw IllegalStateException("카드가 더 이상 없습니다.")
                return availableCards.removeFirst()
            }

            fun reset() {
                availableCards.clear()
                availableCards.addAll(initialCards)
            }
        }

    beforeEach {
        mockCardPicker.reset() // 각 테스트 실행 전에 CardPicker 상태 초기화
    }

    val gameManager = GameManager(cardPicker = mockCardPicker)

    test("초기 카드를 나눠주면 각 플레이어는 두 장씩 카드를 받는다") {
        // given
        val playerNames = listOf("dongyeon", "pobi")

        // when
        val playerCards = gameManager.pickFirstCards(playerNames)

        // then
        playerCards.shouldHaveSize(playerNames.size)
        playerCards.forEach { playerCard ->
            playerCard.allCards.shouldHaveSize(2)
        }
    }

    test("플레이어의 점수가 BLACKJACK_SCORE 미만이면 카드를 한 장 더 받을 수 있다") {
        // given
        val initialPlayerCards =
            listOf(
                Card(CardNumber.TWO, CardType.HEART),
                Card(CardNumber.THREE, CardType.HEART),
            )
        val player = Player.of("dongyeon", initialPlayerCards)

        // when
        val updatedPlayerCard = gameManager.pickCardIfValid(player)

        // then
        updatedPlayerCard.allCards.shouldHaveSize(3)
        updatedPlayerCard.calculateScore().shouldBeGreaterThanOrEqual(player.calculateScore())
    }

    test("플레이어의 점수가 BLACKJACK_SCORE 이상이면 카드를 추가로 받지 않는다") {
        // given
        val initialPlayerCards =
            listOf(
                Card(CardNumber.JACK, CardType.HEART),
                Card(CardNumber.KING, CardType.HEART),
                Card(CardNumber.ACE, CardType.HEART),
            )
        val player = Player.of("dongyeon", initialPlayerCards)

        // when
        val updatedPlayerCard = gameManager.pickCardIfValid(player)

        // then
        updatedPlayerCard.allCards.shouldHaveSize(3)
        updatedPlayerCard.allCards.shouldBe(player.allCards)
    }

    test("카드를 받을 때 중복된 카드가 추가되지 않는다") {
        // given
        val initialPlayerCards =
            listOf(
                Card(CardNumber.TWO, CardType.HEART),
            )
        val player = Player.of("dongyeon", initialPlayerCards)

        // when
        val updatedPlayerCard = gameManager.pickCardIfValid(player)

        // then
        updatedPlayerCard.allCards.distinct().shouldHaveSize(2)
    }
})
