package blackjack

import blackjack.business.AlwaysDrawStrategy
import blackjack.business.CardFixture
import blackjack.business.FixSelectionStrategy
import blackjack.business.card.Card
import blackjack.business.card.CardDesk
import blackjack.business.participants.PlayerResult
import blackjack.business.util.Money
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class GameManagerTest {

    /**
     *  dealer: A, 3, 5
     *  pobi: 9, 3, 9
     *  jason: 10, 3, 10
     */
    @Test
    fun start() {
        // given
        val view = GameManagerTestGameView()
        val gameManager = GameManager(
            view,
            AlwaysDrawStrategy(),
            CardDesk(getCards(), FixSelectionStrategy())
        )

        // when
        gameManager.start()

        // then
        view.playerNames shouldBe listOf("pobi", "jason")
        view.gameResult.dealerResult shouldBe PlayerResult("딜러", Money(-10000))
        view.gameResult.playerResults shouldContainExactlyInAnyOrder listOf(
            PlayerResult("pobi", Money(20000)),
            PlayerResult("jason", Money(-10000))
        )
    }
}

class MainTest {

    private val standardOut = System.out
    private val outputStreamCaptor = ByteArrayOutputStream()

    @BeforeEach
    fun setUp() {
        System.setOut(PrintStream(outputStreamCaptor))
    }

    @AfterEach
    fun tearDown() {
        System.setOut(standardOut)
    }

    @Test
    fun main() {
        // given
        val gameManager = GameManager(
            MainTestGameView(),
            AlwaysDrawStrategy(),
            CardDesk(getCards(), FixSelectionStrategy())
        )

        // when
        gameManager.start()

        // then
        outputStreamCaptor.toString() shouldBe """
            게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)
            pobi, jason
            pobi 의 배팅 금액은?
            10000
            jason 의 배팅 금액은?
            10000
            
            pobi, jason에게 2장의 카드를 나누었습니다.
            딜러카드: A스페이드, 3스페이드
            pobi카드: 9스페이드, 3스페이드
            jason카드: 10스페이드, 3스페이드
            
            pobi카드: 9스페이드, 3스페이드, 9스페이드
            jason카드: 10스페이드, 3스페이드, 10스페이드
            
            딜러는 16이하라 한장의 카드를 더 받았습니다.
            딜러카드: A스페이드, 3스페이드, 5스페이드 - 결과: 19
            pobi카드: 9스페이드, 3스페이드, 9스페이드 - 결과: 21
            jason카드: 10스페이드, 3스페이드, 10스페이드 - 결과: 23
            
            ## 최종 수익
            딜러: -10000
            pobi: 20000
            jason: -10000
            
            """.trimIndent()
    }
}

private fun getCards(): List<Card> {
    return listOf(
        // dealer
        CardFixture.SPACE_ACE, CardFixture.SPACE_THREE,
        // pobi
        CardFixture.SPACE_NINE, CardFixture.SPACE_THREE,
        // jason
        CardFixture.SPACE_TEN, CardFixture.SPACE_THREE,
        // pobi
        CardFixture.SPACE_NINE,
        // jason
        CardFixture.SPACE_TEN,
        // dealer
        CardFixture.SPACE_FIVE
    )
}
