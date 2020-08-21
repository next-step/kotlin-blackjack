package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GameTest {
    private lateinit var normalGame: Game
    private lateinit var deckCards: Set<Card>
    private lateinit var firstPlayer: Player
    private lateinit var secondPlayer: Player

    @BeforeEach
    fun setUp() {
        deckCards = setOf(
            Card(CardScore.TWO, Suit.HEART),
            Card(CardScore.THREE, Suit.HEART),
            Card(CardScore.FOUR, Suit.HEART),
            Card(CardScore.FIVE, Suit.HEART),
            Card(CardScore.SIX, Suit.HEART),
            Card(CardScore.SEVEN, Suit.HEART),
            Card(CardScore.EIGHT, Suit.HEART),
            Card(CardScore.TWO, Suit.DIAMOND)
        )

        normalGame = Game("first, second", Dealer(Deck(deckCards)))

        firstPlayer = normalGame.players.findPlayer(0)
        secondPlayer = normalGame.players.findPlayer(1)
    }

    @Test
    fun `게임 시작시 딜러와 플레이어에게 카드 2장씩 지급한다`() {
        assertThat(normalGame.dealer.cardCount()).isEqualTo(2)
        assertThat(firstPlayer.cardCount()).isEqualTo(2)
        assertThat(secondPlayer.cardCount()).isEqualTo(2)
    }

    @Test
    fun `HIT면 카드 한장을 뽑고, STAY면 그대로 넘어간다 (디폴트 카드 - 2장)`() {
        // when
        normalGame.giveChanceToDraw(reply = YES)
        normalGame.giveChanceToDraw(reply = NO)

        // then
        assertThat(firstPlayer.cardCount()).isEqualTo(3)
        assertThat(firstPlayer.cardCount()).isEqualTo(3)
    }

    @Test
    fun `딜러 점수가 17 미만이면 카드 한 장을 뽑는다"`() {
        // when
        val dealer = normalGame.playOfDealer()

        assertThat(dealer.cardCount()).isEqualTo(3)
    }

    @Test
    fun `현재 진행 중인 플레이어를 찾는다`() {
        assertTrue(normalGame.currentPlayer() == firstPlayer)
    }

    @Test
    fun `턴이 끝나면 게임이 종료된다`() {
        // when
        normalGame.giveChanceToDraw(NO)
        normalGame.giveChanceToDraw(NO)

        // then
        assertTrue(normalGame.isOver)
    }
}
